import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static String ethernetToken[];

    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);

        for(int i=0; i<5; i++){
            System.out.println("[Example input and output #" + (i+1) + "]");
            System.out.println("\t// Find IPv6 address for prefix, subnet, and Ethernet address");

            System.out.print("\t> Input organization block: ");
            StringTokenizer orgBlock = new StringTokenizer(sc.nextLine(), "/");

            System.out.print("\t> Input subnet identifier: ");
            StringTokenizer subnet = new StringTokenizer(sc.nextLine(),":");

            System.out.print("\t> Input Ethernet address: ");
            StringTokenizer ethernet = new StringTokenizer(sc.nextLine(), "-");

            // ipv6의 기관 블록 초기화
            String ipv6 = orgBlock.nextToken() + ":";

            // 기존 ipv6에 서브넷 식별자를 추가해준다.
            while(subnet.hasMoreTokens()){
                ipv6 += subnet.nextToken() +":";
            }

            // ethernet 옥텟 별로 분리해서 담기 위한 배열
            ethernetToken = new String[6];

            // ethernet 옥텟 별로 분리
            for (int j = 0; ethernet.hasMoreTokens(); j++)
                ethernetToken[j] = ethernet.nextToken();

            golbalLocalChange();    // 7번째 비트 1로 만들어줌

            // 마지막으로 변환된 ethernet 주소를 추가해준다.
            ipv6 += ethernetToken[0] + ethernetToken[1] + ":" + ethernetToken[2] + "FF:" + "FE" + ethernetToken[3] + ":" +
                    ethernetToken[4] + ethernetToken[5] + "/128";

            System.out.println("\t> Mapped IPv6 address: " + ipv6 + "\n");
        }

    }

    public static String binaryToHex(String binary){

        if(binary.equals("0010"))
            return "2";
        else if(binary.equals("0011"))
            return "3";
        else if(binary.equals("0111"))
            return "7";
        else if(binary.equals("1010"))
            return "A";
        else if(binary.equals("1111"))
            return "D";
        else if(binary.equals("1111"))
            return "F";

        return "";
    }

    public static void golbalLocalChange(){
        // 첫 번째 옥텟의 2번째 비트 추출 해서 10진수로 변환
        int tmp = Integer.parseInt(String.valueOf(ethernetToken[0].charAt(1)),16);

        //10진수 -> 2진수 변환
        String binary = Integer.toString(tmp,2);
        // MSB가 0이면  세 비트만 변환되는거 수정
        binary = (binary.length() <= 3) ? "0" + binary : binary;

        // 추출한 7번째 비트를 1로 변환
        binary = binary.substring(0, 2) + "1" + binary.substring(3);

        // 변환된 첫 번째 옥텟 저장
        ethernetToken[0] = ethernetToken[0].charAt(0) + binaryToHex(binary);
    }


}


