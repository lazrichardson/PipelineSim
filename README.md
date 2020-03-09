# PipelineSim
This project implements a limited simulation of a pipelined datapath. 

It takes hexidecimal instructions and runs them through a pipelined datapath:
## The pipelined datapath instructions are:
IF, ID, EX, MEM, and WB

## The instructions implemented are:
nop, add, sub, sb and lb

## The test instructions are:
0xa1020000
0x810AFFFC  
0x00831820  
0x01263820  
0x01224820  
0x81180000  
0x81510010 
0x006240220
0x00000000 # This is a nop, used just to allow the "real" instructions finish in the pipeline                           
0x00000000 # This is a nop, used just to allow the "real" instructions finish in the pipeline                            
0x00000000 # This is a nop, used just to allow the "real" instructions finish in the pipeline 
