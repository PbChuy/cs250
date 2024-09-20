package cs250.hw1;


public class Operations {
    public static void main(String[] args){
        if(args.length != 3) {
            System.out.println("Incorrect number of arguments have been provided. Program Terminating!");
            System.exit(1);
        }

        System.out.println("Task 1 Correct number of arguments given.");

        long[] parsedArgs = new long[3];
        String[] numberingSystems = new String[3];
        String[] binaryArgs = new String[3];

        System.out.println();


        System.out.println("Task 2 ");
            for (int i = 0; i < args.length; i++){
                String arg = args[i];
                if(arg.startsWith("0b") || arg.startsWith("0B")) { // checking to make sure its a binary #
                    parsedArgs[i] = parseBinary(arg.substring(2));
                    numberingSystems[i] = "Binary";
                }else if (arg.startsWith("0x") || arg.startsWith("0X")){ // make sure its in hexadecimal 
                    parsedArgs[i] = parseHexadecimal(arg.substring(2));
                    numberingSystems[i] = "Hexadecimal";
                }else {
                    parsedArgs[i] = parseDecimal(arg);
                    numberingSystems[i] ="Decimal";
                }
                System.out.println(arg + "=" + numberingSystems[i] + " ");
                binaryArgs[i] = toBinary(parsedArgs[i]);
            }
            System.out.println();

            System.out.println("Task 3 ");
            for(int i = 0; i < args.length; i++){
                System.out.print(args[i] + "=" + "\n");
            }
            System.out.println();
            
            

            System.out.println("Task 4 ");
            for(int i = 0; i < args.length; i++){
                System.out.print("Start=" + args[i] + "Binary=0b" + binaryArgs[i] + ",Decimal=" + parsedArgs[i] + "Hexadecimal=0x" + toHexadecimal(parsedArgs[i]) + "\n");
            }
            System.out.println();
            

            System.out.println("Task 6 ");
            for(int i = 0; i < args.length; i++){
                System.out.print(args[i] + "=" + binaryArgs[i] + "=>" + twosComplement(binaryArgs[i]) + "\n");
            }
            System.out.println();
            
            System.out.println("Task 7 ");
            System.out.print(binaryArgs[0] + "|" + binaryArgs[1] + "|" + binaryArgs[2] + "=" + bitwiseOR(binaryArgs[0], binaryArgs[1], binaryArgs[2]) + "\n");
            System.out.print(binaryArgs[0] + "&" + binaryArgs[1] + "&" + binaryArgs[2] + "=" + bitwiseAND(binaryArgs[0], binaryArgs[1], binaryArgs[2]) + "\n");
            System.out.print(binaryArgs[0] + "^" + binaryArgs[1] + "^" + binaryArgs[2] + "=" + bitwiseXOR(binaryArgs[0], binaryArgs[1], binaryArgs[2]) + "\n");
            System.out.println();
            
            System.out.println("Task 8 ");
            for(int i = 0; i < args.length; i++){
                System.out.print(binaryArgs[i] + "<<2=" + leftShit(binaryArgs[i], 2) + "," + binaryArgs[i] + ">>2=" + rightShift(binaryArgs[i], 2) + "\n");
            }
            System.out.println();
            System.out.println();
        }

    private static long parseBinary(String binary){
        for (char c : binary.toCharArray()) {
            if (c != '0' && c != '1'){
                throw new IllegalArgumentException("Invalid binary number." + binary);
            }
        }

        return Long.parseLong(binary, 2);
    }

    private static long parseDecimal(String decimal){
        for(char c : decimal.toCharArray()){
            if(c < '0' || c > '9'){
                throw new IllegalArgumentException("Invalid decimal" + decimal);
            }
        }
        return Long.parseLong(decimal);
        
    }

    private static long parseHexadecimal (String hex){
        for (char c : hex.toCharArray()){
            if (!((c >= '0' && c <= '9' || (c >= 'a' && c <= 'f'|| c>='A' && c<= 'F')))){
                throw new IllegalArgumentException("Invald hexadecimal" + hex);
            }
        }

        return Long.parseLong(hex, 16);
    }

    private static String toBinary(long number){
        return Long.toBinaryString(number);
        
    }

    private static String toHexadecimal(long number){
        if (number == 0) return "0" ;
        StringBuilder hex = new StringBuilder();
        while (number > 0) {
            int digit = (int) (number & 0xF);
            hex.insert(0, (char) (digit < 10 ? '0' + digit : 'A' + digit - 10));
            number >>>= 4;
        }
        return hex.toString();
    }

    private static String onesComplement(String binary){
        StringBuilder complement = new StringBuilder();
        for( char bit: binary.toCharArray()){
            complement.append(bit == '0' ? '1' : '0');
        }
        return complement.toString();
    }

    private static String twosComplement(String binary ) {
        String onesComp = onesComplement(binary);
        StringBuilder twosComp = new StringBuilder(onesComp);

        int carry = 1;
        for (int i = twosComp.length() - 1; i >= 0; i--){
            if(twosComp.charAt(i) == '1' && carry == 1){
                twosComp.setCharAt(i, '0');
            } else if(twosComp.charAt(i) == '0' && carry == 1){
                twosComp.setCharAt(i, '1');
                carry = 0;
                break;
            }
        }

        if (carry == 1) {
            twosComp.insert(0, '1');
        }

        return twosComp.toString();
    }

    private static String padBinary(String binary, int length){
        return String.format("%" + length + "s", binary).replace(' ', '0');
    }

    private static String bitwiseOR(String a, String b, String c){
        StringBuilder result = new StringBuilder();
        for( int i = 0; i < a.length(); i++){
            result.append((a.charAt(i) == '1' || b.charAt(i) == '1' || c.charAt(i) == '1') ? '1' : '0');
        }
        return result.toString();
    }

    private static String bitwiseAND(String a, String b, String c){
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < a.length(); i++){
            result.append((a.charAt(i) == '1' && b.charAt(i) == '1' && c.charAt(i) == '1') ? '1' : '0');
        }
        return result.toString();
    }
    
    private static String bitwiseXOR( String a, String b, String c){
        StringBuilder result = new StringBuilder();
        for (int i = 0 ; i < a.length(); i++){
            int count = (a.charAt(i) == '1' ? 1 : 0) + (b.charAt(i) == '1' ? 1 : 0) + (c.charAt(i) == '1' ? 1 : 0);
            result. append((count == 1 || count == 3) ? '1' : '0');
        }
        return result.toString();
    }

    private static String leftShit(String binary, int shifts){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < shifts; i++){
            result.append('0');
        }
        return result.toString();
    }

    private static String rightShift(String binary, int shifts) {
        if (shifts >= binary.length()){
            return "0".repeat(binary.length());
        }
        return "0".repeat(shifts) + binary.substring(0, binary.length() - shifts);
    }
}