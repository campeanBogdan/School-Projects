set_property PACKAGE_PIN R2 [get_ports SEL]
    set_property IOSTANDARD LVCMOS33 [get_ports SEL]
    
set_property CLOCK_DEDICATED_ROUTE FALSE [get_nets BUTTON_3]

set_property PACKAGE_PIN U17 [get_ports BUTTON_3]
    set_property IOSTANDARD LVCMOS33 [get_ports BUTTON_3]
    
set_property CLOCK_DEDICATED_ROUTE FALSE [get_nets BUTTON_1]

set_property PACKAGE_PIN T17 [get_ports BUTTON_1]
    set_property IOSTANDARD LVCMOS33 [get_ports BUTTON_1]
    
set_property PACKAGE_PIN L1 [get_ports SEMN_OUT]
    set_property IOSTANDARD LVCMOS33 [get_ports SEMN_OUT]
    
set_property PACKAGE_PIN P1 [get_ports {Q[6]}]
    set_property IOSTANDARD LVCMOS33 [get_ports {Q[6]}]
    
set_property PACKAGE_PIN N3 [get_ports {Q[5]}]
    set_property IOSTANDARD LVCMOS33 [get_ports {Q[5]}]
            
set_property PACKAGE_PIN P3 [get_ports {Q[4]}]
    set_property IOSTANDARD LVCMOS33 [get_ports {Q[4]}]
        
set_property PACKAGE_PIN U3 [get_ports {Q[3]}]
    set_property IOSTANDARD LVCMOS33 [get_ports {Q[3]}]
        
set_property PACKAGE_PIN W3 [get_ports {Q[2]}]
    set_property IOSTANDARD LVCMOS33 [get_ports {Q[2]}]
        
set_property PACKAGE_PIN V3 [get_ports {Q[1]}]
    set_property IOSTANDARD LVCMOS33 [get_ports {Q[1]}]
    
set_property PACKAGE_PIN V13 [get_ports Q[0]]
    set_property IOSTANDARD LVCMOS33 [get_ports Q[0]]
    
set_property PACKAGE_PIN W5 [get_ports CLK]
    set_property IOSTANDARD LVCMOS33 [get_ports CLK]
        create_clock -add -name sys_clk_pin -period 10 -waveform {0 5} [get_ports CLK]
        
set_property PACKAGE_PIN U2 [get_ports {ANOD[0]}]
    set_property IOSTANDARD LVCMOS33 [get_ports {ANOD[0]}]
    
set_property PACKAGE_PIN U4 [get_ports {ANOD[1]}]
    set_property IOSTANDARD LVCMOS33 [get_ports {ANOD[1]}]
    
set_property PACKAGE_PIN V4 [get_ports {ANOD[2]}]
    set_property IOSTANDARD LVCMOS33 [get_ports {ANOD[2]}]
   
set_property PACKAGE_PIN W4 [get_ports {ANOD[3]}]
    set_property IOSTANDARD LVCMOS33 [get_ports {ANOD[3]}]
    
set_property PACKAGE_PIN W7 [get_ports {CATOD[0]}]
    set_property IOSTANDARD LVCMOS33 [get_ports {CATOD[0]}]
   
set_property PACKAGE_PIN W6 [get_ports {CATOD[1]}]
    set_property IOSTANDARD LVCMOS33 [get_ports {CATOD[1]}]
    
set_property PACKAGE_PIN U8 [get_ports {CATOD[2]}]
    set_property IOSTANDARD LVCMOS33 [get_ports {CATOD[2]}]
    
set_property PACKAGE_PIN V8 [get_ports {CATOD[3]}]
    set_property IOSTANDARD LVCMOS33 [get_ports {CATOD[3]}]
    
set_property PACKAGE_PIN U5 [get_ports {CATOD[4]}]
    set_property IOSTANDARD LVCMOS33 [get_ports {CATOD[4]}]
    
set_property PACKAGE_PIN V5 [get_ports {CATOD[5]}]
    set_property IOSTANDARD LVCMOS33 [get_ports {CATOD[5]}]
    
set_property PACKAGE_PIN U7 [get_ports {CATOD[6]}]
    set_property IOSTANDARD LVCMOS33 [get_ports {CATOD[6]}]
    
set_property CLOCK_DEDICATED_ROUTE FALSE [get_nets BUTTON_5]
  
set_property PACKAGE_PIN U18 [get_ports BUTTON_5]
    set_property IOSTANDARD LVCMOS33 [get_ports BUTTON_5]
    
set_property CLOCK_DEDICATED_ROUTE FALSE [get_nets BUTTON_2]

set_property PACKAGE_PIN T18 [get_ports BUTTON_2]
    set_property IOSTANDARD LVCMOS33 [get_ports BUTTON_2]