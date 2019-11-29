public class Instruction {

    /*
   opcode, is always contained in bits 31:26.
            ■ The two registers to be read are always specified by the rs and rt fields, at positions 25:21 and 20:16.
                This is true for the R-type instructions, branch equal, and store.
            ■ The base register for load and store instructions is always in bit positions 25:21 (rs).
            ■ The 16-bit offset for branch equal, load, and store is always in positions 15:0.
            ■ The destination register is in one of two places. For a load it is in bit positions 20:16 (rt), while for an R-type instruction it is in bit positions 15:11 (rd).
                Thus, we will need to add a multiplexor to select which field of the instruction is used to indicate the register number to be written.
     */

    int instr;
    int opCode;
    int regSrcOne;
    int regSrcTwo;
    int rFormatRegDest; // r type only
    int rFormatFunc; // r type only
    int iFormatOffset;
    private String codeName;

    public Instruction(int instr) {
        this.instr = instr;
        parseCodes();
        findCodeName();
    }

    public int getInstr() {
        return instr;
    }

    public void setInstr(int instr) {
        this.instr = instr;
    }

    public int getOpCode() {
        return opCode;
    }

    public void setOpCode(int opCode) {
        this.opCode = opCode;
    }

    public int getRegSrcOne() {
        return regSrcOne;
    }

    public void setRegSrcOne(int regSrcOne) {
        this.regSrcOne = regSrcOne;
    }

    public int getRegSrcTwo() {
        return regSrcTwo;
    }

    public void setRegSrcTwo(int regSrcTwo) {
        this.regSrcTwo = regSrcTwo;
    }

    public int getrFormatRegDest() {
        return rFormatRegDest;
    }

    public void setrFormatRegDest(int rFormatRegDest) {
        this.rFormatRegDest = rFormatRegDest;
    }

    public int getrFormatFunc() {
        return rFormatFunc;
    }

    public void setrFormatFunc(int rFormatFunc) {
        this.rFormatFunc = rFormatFunc;
    }

    public int getiFormatOffset() {
        return iFormatOffset;
    }

    public void setiFormatOffset(int iFormatOffset) {
        this.iFormatOffset = iFormatOffset;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    // opcode translations
    public void findCodeName() {
        if (opCode == 0) {
            if (rFormatFunc == 0x20) {
                codeName = "add";
            } else if (rFormatFunc == 0x22) {
                codeName = "sub";
            } else if (rFormatFunc == 0) {
                codeName = "nop";
            }
        } else if (opCode == 0x20) {
            codeName = "lb";
        } else if (opCode == 0x28) {
            codeName = "sb";
        }
    }

    public void parseCodes() {

        int maskOpCode = 0xFC000000;    // maskOpCode
        opCode = instr & maskOpCode;
        opCode = opCode >>> (26);

        int maskRegSrcOne = 0x03E00000;    // R-Format   maskS1
        regSrcOne = maskRegSrcOne & instr;
        regSrcOne = regSrcOne >>> (21);

        int maskRegSrcTwo = 0x001F0000;    // R-Format   maskS2Dst
        regSrcTwo = maskRegSrcTwo & instr;
        regSrcTwo = regSrcTwo >>> (16);

        // r type parse
        int rFormatMaskRegDest = 0x0000F800;    // R-Format   maskDst
        rFormatRegDest = rFormatMaskRegDest & instr;
        rFormatRegDest = rFormatRegDest >>> (11);

        int rFormatMaskFunc = 0x0000003F;    // R-Format   maskFunc
        rFormatFunc = rFormatMaskFunc & instr;

        // i format specific parse
        int iFormatMaskOffset = 0x0000FFFF; // I-Format   maskOffset
        iFormatOffset = (short) (iFormatMaskOffset & instr);
    }

    // main output for the assignment
    public void printDetailCodes() {

        // add: add $d, $s, $t
        if (codeName.equals("add")) {
            System.out.print(codeName + " $" + rFormatRegDest + ", $" + regSrcOne + ", $" + regSrcTwo);
        }
        //sub: sub $d, $s, $t
        else if (codeName.equals("sub")) {
            System.out.print(codeName + " $" + rFormatRegDest + ", $" + regSrcOne + ", $" + regSrcTwo);
        }
        //lb: lb $t, offset($s)
        else if (codeName.equals("lb")) {
            System.out.print(codeName + " $" + rFormatRegDest + ", " + iFormatOffset + " ($" + regSrcOne + ")");
        }
        //sb: sb $t, offset($s)
        else if (codeName.equals("sb")) {
            System.out.print(codeName + " $" + rFormatRegDest + ", " + iFormatOffset + " ($" + regSrcOne + ")");
        }
        //nop: nop
        else if (codeName.equals("nop")) {
            System.out.print(codeName);
        }
    }
}
