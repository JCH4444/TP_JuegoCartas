package juegocartas;

import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Carta {

    private final int indice;
    private boolean formaGrupo;

    public boolean estaEnGrupo() {
        return formaGrupo;
    }

    public void setFormaGrupo(boolean formaGrupo) {
        this.formaGrupo = formaGrupo;
    }

    public Carta(Random r) {
        indice = r.nextInt(52) + 1;
    }

    @Override
    public String toString() {
        return "Carta{" +getNombre()+  "pinta=" + getPinta() +"indice=" 
                + indice + ", formaGrupo=" + formaGrupo + '}';
    }
    
    public Pinta getPinta() {
        if (indice <= 13) {
            return Pinta.TREBOL;
        } else if (indice <= 26) {
            return Pinta.PICA;
        } else if (indice <= 39) {
            return Pinta.CORAZON;
        } else {
            return Pinta.DIAMANTE;
        }
    }

    public int getIndice() {
        return indice;
    }
    //Generando residuos entre 0 y 12
    public NombreCarta getNombre() {
        int numero = indice % 13;
        if (numero == 0) {
            numero = 13;
        }

        return NombreCarta.values()[numero - 1];
    }

    //Método para mostrar la carta
    public void mostrar(JPanel pnl, int x, int y) {
        String nombreImagen = "/imagenes/carta" + String.valueOf(indice) + ".jpg";

        ImageIcon imagen = new ImageIcon(getClass().getResource(nombreImagen));
        JLabel lbl = new JLabel(imagen);
        lbl.setBounds(x, y, imagen.getIconWidth(), imagen.getIconHeight());

        pnl.add(lbl);
    }
}


