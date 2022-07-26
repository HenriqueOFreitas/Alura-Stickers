import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.awt.Font;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {

    public void cria(InputStream inputStream, String nomeArquivo) throws IOException {

        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();

        int novaAltura = altura + 200;

        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // Configurar a fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, (int) (largura / 15));
        graphics.setFont(fonte);

        graphics.drawString("TOPZERA", (int) (largura / 3), novaAltura - 100);

        // Escrever a nova imagem em um novo arquivo
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));
    }

    public BufferedImage resizeImage(BufferedImage imagemOriginal, int newWidth, int newHeight) throws IOException {
        Image imagemOriginalEdit = imagemOriginal.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        resizedImage.getGraphics().drawImage(imagemOriginalEdit, 0, 0, null);
        return resizedImage;
    }

}