import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class ConsultaCEP {

    public static void main(String[] args) {
        String cep = "01001000"; // Você pode mudar para qualquer CEP válido
        buscarCEP(cep);
    }

    public static void buscarCEP(String cep) {
        try {
            String url = "https://viacep.com.br/ws/" + cep + "/json/";
            HttpURLConnection conexao = (HttpURLConnection) new URL(url).openConnection();
            conexao.setRequestMethod("GET");

            if (conexao.getResponseCode() == 200) {
                BufferedReader leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
                StringBuilder resposta = new StringBuilder();
                String linha;
                
                while ((linha = leitor.readLine()) != null) {
                    resposta.append(linha);
                }
                leitor.close();

                // Convertendo a resposta para JSON
                JSONObject json = new JSONObject(resposta.toString());

                // Exibindo os dados retornados
                System.out.println("CEP: " + json.getString("cep"));
                System.out.println("Logradouro: " + json.getString("logradouro"));
                System.out.println("Bairro: " + json.getString("bairro"));
                System.out.println("Cidade: " + json.getString("localidade"));
                System.out.println("Estado: " + json.getString("uf"));
            } else {
                System.out.println("Erro na conexão: Código " + conexao.getResponseCode());
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
