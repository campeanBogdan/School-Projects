# 
# Synthesis run script generated by Vivado
# 

set_param xicom.use_bs_reader 1
set_msg_config -id {HDL 9-1061} -limit 100000
set_msg_config -id {HDL 9-1654} -limit 100000
create_project -in_memory -part xc7a35tcpg236-1

set_param project.singleFileAddWarning.threshold 0
set_param project.compositeFile.enableAutoGeneration 0
set_param synth.vivado.isSynthRun true
set_property webtalk.parent_dir C:/Users/Bogdan/project_24/project_24.cache/wt [current_project]
set_property parent.project_path C:/Users/Bogdan/project_24/project_24.xpr [current_project]
set_property default_lib xil_defaultlib [current_project]
set_property target_language Verilog [current_project]
set_property ip_output_repo c:/Users/Bogdan/project_24/project_24.cache/ip [current_project]
set_property ip_cache_permissions {read write} [current_project]
read_vhdl -library xil_defaultlib C:/Users/Bogdan/project_24/project_24.srcs/sources_1/new/proiect.vhd
foreach dcp [get_files -quiet -all *.dcp] {
  set_property used_in_implementation false $dcp
}
read_xdc C:/Users/Bogdan/project_24/project_24.srcs/constrs_1/new/f.xdc
set_property used_in_implementation false [get_files C:/Users/Bogdan/project_24/project_24.srcs/constrs_1/new/f.xdc]


synth_design -top nr -part xc7a35tcpg236-1


write_checkpoint -force -noxdef nr.dcp

catch { report_utilization -file nr_utilization_synth.rpt -pb nr_utilization_synth.pb }
