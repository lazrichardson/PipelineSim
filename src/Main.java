public class Main {

    public static void main(String[] args) {

        Pipeline pipeline = new Pipeline();

        int cycle = 0;

        while (cycle < pipeline.instructionCache.length) {
            // IF     ------------------------------------------------------------------------------------------------------
            IF_Stage if_stage = new IF_Stage(
                    pipeline.getCycleCount(), pipeline);

            // ID     ------------------------------------------------------------------------------------------------------
            ID_Stage id_stage = new ID_Stage(
                    if_stage.getIfWrite_ProgramCounter(),
                    if_stage.getIfWrite_Instruction(),
                    pipeline.getMemory());
            /*
            // EX     ------------------------------------------------------------------------------------------------------
            EX_Stage ex_stage = new EX_Stage(
                    id_stage.getIdWrite_ControlSignal(),
                    id_stage.getIdWrite_ProgramCounter(),
                    id_stage.getIdWrite_ReadData1(),
                    id_stage.getIdWrite_ReadData2(),
                    id_stage.getIdWrite_SignExtendedOffset(),
                    id_stage.getIdWrite_WriteRegister_20_16(),
                    id_stage.getIdWrite_WriteRegister_15_11(),
                    id_stage.getIdWrite_RFormatFunc());

            // MEM     -----------------------------------------------------------------------------------------------------
            MEM_Stage mem_stage = new MEM_Stage(
                    ex_stage.getExWrite_AluZero(),
                    ex_stage.getExWrite_AluResult(),
                    ex_stage.getExWrite_SwValue(),
                    ex_stage.getExWrite_WriteRegNum(),
                    ex_stage.getExWrite_ControlSignal());

            // WB READ----------------------------------------------------------------------------------------------------
            WB_Stage wb_stage = new WB_Stage(
                    mem_stage.getMemWrite_ControlSignal(),
                    mem_stage.getMemWrite_LWDataValue(),
                    mem_stage.getMemWrite_AluResult(),
                    mem_stage.getMemWrite_WriteRegNum(),
                    pipeline.getMemory());

             */
        cycle = cycle + 1;
        }
    }

}  // class end
