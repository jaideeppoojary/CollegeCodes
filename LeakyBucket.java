import java.util.Scanner;
public class LeakyBucket {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("\n [+] Enter the number of packets:  ");
        int numberOfPacket = input.nextInt();

        int[] packets = new int[numberOfPacket];
        System.out.print("\n [+] Enter the size of the packets:  ");

        for( int i=0; i < numberOfPacket; i++){
            packets[i] = input.nextInt();
        }

        System.out.print("\n [+] Enter Bucket size: ");
        int bucketSize = input.nextInt();
        System.out.print("\n [+] Enter Output rate: ");
        int outputRate = input.nextInt();

        // Main logic

        int remainingPacket = 0;
        int acceptPacket,sent;
        int totalPacket;

        System.out.println("    +-------------------------------------------------+");
        System.out.println("    | Clock | P.Size | Accept/Drop | Sent | Remaining |");
        System.out.println("    +-------------------------------------------------+");


        for( int i=0; i< packets.length; i++){

            totalPacket = packets[i] + remainingPacket;
            
            // Accept or Drop
            // if packet accept
            if( totalPacket <= bucketSize ){
                acceptPacket = packets[i];
                
                // packet grater than output rate
                if( totalPacket > outputRate){
                    sent = outputRate;
                }
                else{
                    // packet less than or = output rate
                    sent = totalPacket;
                    
                }
                
                remainingPacket = totalPacket - sent;
            }
            // if packet drop
            else{
                acceptPacket = 0;
                sent =0;
                remainingPacket =0;
            }

            System.out.println("    | "+(i+1)+"     |   "+packets[i]+"    |      "+((acceptPacket!=0)? acceptPacket: "D")+"      |    "+sent+" |    "+remainingPacket+"      |");
 
        }
        System.out.println("    +-------------------------------------------------+");

    }
}