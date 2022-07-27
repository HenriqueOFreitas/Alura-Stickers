import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        
        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/NASA-APOD.json";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoNasa();

        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/TopMovies.json";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoIMDB();

        String url = "https://alura-imersao-api-linguagens.herokuapp.com/linguagens";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoIMDB();

        var http = new ClienteHttp();
        String json =  http.buscaDados(url);

        
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();

        for(int i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeDoArquivo = "saida/" + conteudo.getTitulo() + ".png";

            
            geradora.cria(inputStream, nomeDoArquivo);

            System.out.println("Título: " + conteudo.getTitulo());
            System.out.println("Poster: " + conteudo.getUrlImagem());
            System.out.println();
        }
    }
}
