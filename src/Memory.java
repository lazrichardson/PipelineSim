public class Memory {

    int[] Regs = new int[32];
    int [] Main_Mem = new int [1024];


    public Memory() {
        // initialize the cache

        for (int i = 0; i < Regs.length; i++) {
            // int regStart = 0x100; change this back
            int regStart = 0x30000;
            if (i == 0){
                Regs[i] = 0;
            }
            else{
                Regs[i] = regStart + i;
            }
        }
        // initialize the main mainMemory
        short counter = 0x0;
        for (int i = 0; i < Main_Mem.length; i++) {
            Main_Mem[i] = counter;
            counter++;
            if (counter == (0xFF + 1)) {
                counter = 0;
            }
        }
    }

    // main mainMemory test print
    public void mainMemPrint() {
        for (int i = 0; i < Main_Mem.length; i++) {
            System.out.println(Integer.toHexString(i) + " Value: " + Integer.toHexString(Main_Mem[i]));
        }
    }

    public void regsPrint() {
        for (int i = 0; i < Regs.length; i++) {
            System.out.println(i + " Value: " + Integer.toHexString(Regs[i]));
        }
    }


}
