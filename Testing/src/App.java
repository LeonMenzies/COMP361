public class App {
    public static void main(String[] args){
        App.run(1, 2);
    }


    public static void run(int i, int j){

        for(int x = 0; x < 2 * i; x += 2){
            for(int y = 0; y < j * 3; y += 3){
                System.out.println("top: x=" + x + " y="+ y);
                System.out.println("bot: x=" + x +" y=" + (y+1));
            }
        }

    }
}
