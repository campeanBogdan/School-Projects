----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 02/25/2018 10:12:04 AM
-- Design Name: 
-- Module Name: ex1 - Behavioral
-- Project Name: 
-- Target Devices: 
-- Tool Versions: 
-- Description: 
-- 
-- Dependencies: 
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
-- 
----------------------------------------------------------------------------------


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;


entity ex1 is
    Port ( btn : in STD_LOGIC;
           dir : in STD_LOGIC;
           rst : in STD_LOGIC;
           led : out STD_LOGIC_VECTOR (3 downto 0) := "0000");
end ex1;

architecture Behavioral of ex1 is

shared variable x : integer := 0;

begin

    process (btn)
    begin
    
        if rising_edge(btn) then
            if dir = '0' then
                x := x + 1;
            else
                x := x - 1;
            end if;
        end if;
        
        if x = 16 or rst = '1' then
            x := 0;
        end if;
        
    end process;           
    
    process (btn) 
    begin
    
        case x is 
            when 0 => led <= "0000";
            when 1 => led <= "0001";
            when 2 => led <= "0010";
            when 3 => led <= "0011";
            when 4 => led <= "0100";
            when 5 => led <= "0101";
            when 6 => led <= "0110";
            when 7 => led <= "0111";
            when 8 => led <= "1000";
            when 9 => led <= "1001";
            when 10 => led <= "1010";
            when 11 => led <= "1011";
            when 12 => led <= "1100";
            when 13 => led <= "1101";
            when 14 => led <= "1110";
            when 15 => led <= "1111";
            when others => led <= "0000";
        end case;
    end process;

end Behavioral;
