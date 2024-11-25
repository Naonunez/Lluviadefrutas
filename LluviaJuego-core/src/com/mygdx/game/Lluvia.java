package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Lluvia implements Actualizable {
    private Array<ObjetoCaida> objetosCaidos;
    private Array<Texture> frutas;
    private Texture bomba;
    private Sound dropSound;
    private Music rainMusic;
    private long lastDropTime;

    public Lluvia(Array<Texture> frutas, Texture bomba, Sound ss, Music mm) {
        this.frutas = frutas;
        this.bomba = bomba;
        this.dropSound = ss;
        this.rainMusic = mm;
    }

    public void crear() {
        objetosCaidos = new Array<>();
        crearObjetoCaido();
        rainMusic.setLooping(true);
        rainMusic.play();
    }

    private void crearObjetoCaido() {
        ObjetoCaida nuevoObjeto;
        if (MathUtils.randomBoolean(0.1f)) { // 10% de probabilidad de crear una bomba
            nuevoObjeto = new Bomba(bomba);
            ((Bomba) nuevoObjeto).setEstrategiaMovimiento(new MovimientoZigzag()); // Configura zigzag
            System.out.println("Bomba creada en posición inicial: x=" + nuevoObjeto.getArea().x + ", y=" + nuevoObjeto.getArea().y);
        } else { // Frutas
            int tipoFruta = MathUtils.random(0, frutas.size - 1);
            nuevoObjeto = new Fruta(frutas.get(tipoFruta));
            System.out.println("Fruta creada en posición inicial: x=" + nuevoObjeto.getArea().x + ", y=" + nuevoObjeto.getArea().y);
        }
        nuevoObjeto.getArea().setPosition(MathUtils.random(0, 800 - 64), 480); // Posición inicial aleatoria en la parte superior
        objetosCaidos.add(nuevoObjeto);
        lastDropTime = TimeUtils.nanoTime();
    }

    @Override
    public void actualizarMovimiento(Tarro tarro) { 
        // Crear nuevos objetos si ha pasado el tiempo
        if (TimeUtils.nanoTime() - lastDropTime > 100000000) {
            crearObjetoCaido();
        }

        for (int i = 0; i < objetosCaidos.size; i++) {
            ObjetoCaida objeto = objetosCaidos.get(i);

            // Movimiento hacia abajo (caída)
            objeto.getArea().y -= 200 * Gdx.graphics.getDeltaTime();

            // Movimiento zigzag en X solo para bombas
            if (objeto instanceof Bomba) {
                // Asegura que el zigzag afecta la posición del área visible
                objeto.mover();
                objeto.getArea().x = objeto.getX(); // Sincroniza posición x con zigzag
            }

            // Depuración: muestra la posición actual de cada objeto
            System.out.println("Moviendo objeto: x=" + objeto.getArea().x + ", y=" + objeto.getArea().y);

            // Eliminar objetos que salen de la pantalla
            if (objeto.getArea().y + 64 < 0) {
                System.out.println("Objeto removido (fuera de pantalla): x=" + objeto.getArea().x + ", y=" + objeto.getArea().y);
                objetosCaidos.removeIndex(i);
                i--; // Ajustar índice tras eliminación
            } 
            // Detectar colisión con el tarro
            else if (objeto.getArea().overlaps(tarro.getArea())) {
                objeto.efecto(tarro);
                if (objeto instanceof Fruta) dropSound.play();
                objetosCaidos.removeIndex(i);
                i--; // Ajustar índice tras eliminación
            }
        }
    }



    public void actualizarDibujoLluvia(SpriteBatch batch) {
        for (ObjetoCaida objeto : objetosCaidos) {
            batch.draw(objeto.getTextura(), objeto.getArea().x, objeto.getArea().y);
        }
    }

    public void destruir() {
        dropSound.dispose();
        rainMusic.dispose();
    }

    // Método para exponer la lista de objetos caídos
    public Array<ObjetoCaida> getObjetosCaida() {
        return objetosCaidos;
    }
}
