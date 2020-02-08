import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Username {
    public static void main(String[] args) {
        try {
            String url = "http://steamcommunity.com/profiles/76561197998492446";
            URLConnection con = new URL( url ).openConnection();
            System.out.println( "orignal url: " + con.getURL() );
            con.connect();
            System.out.println( "connected url: " + con.getURL() );
            InputStream is = con.getInputStream();
            System.out.println( "redirected url: " + con.getURL() );
            is.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}