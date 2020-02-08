

public class Main {
    public static void main(String[] args) {
        MyHttpClient obj = new MyHttpClient();
        try {
            System.out.println("Testing 1 - Send Http GET request");
            obj.sendGet();

            System.out.println("Testing 2 - Send Http POST request");
            obj.sendPost();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
