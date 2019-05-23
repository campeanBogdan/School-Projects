library IEEE;
use IEEE.STD_LOGIC_1164.ALL;


-- CLK = tactul placutei; 
-- SEL = selectarea directiei de numarare a numaratoarelor in momentul citirii numerelor
-- ANOD, CATOD = iesirile afisorului
-- BUTTON_1 = alegerea numerelor la citire
-- BUTTON_2 = alegerea operatiei de efectuat
-- BUTTON_3 = alegerea semnului la introducerea numerelor
-- BUTTON_5 = trecerea dintr-o stare in alta a programului
 
entity nr is
    generic (NR_BITI: NATURAL := 11);
    port (CLK, BUTTON_1, BUTTON_5, BUTTON_2, BUTTON_3: inout BIT; SEL: inout BIT; ANOD: inout BIT_VECTOR (3 downto 0); CATOD: out BIT_VECTOR (0 to 6);
            Q: inout BIT_VECTOR (2 downto 0) := (others => '0'); SEMN_OUT: out BIT);
end nr;



architecture Behavioral of nr is

type vector is array (NR_BITI - 1 downto 0) of INTEGER;
type vector_1 is array (NR_BITI - 1 downto 0) of BIT;
type vector_5 is array (4 downto 0) of BIT;
type vector_2 is array (1 downto 0) of BIT;


--functiile folosite in proiect

-- functia CIFRA_Z returneaza cifra zecilor ca intreg dintr-un sir de biti transmis ca parametru
function CIFRA_Z (Q: vector_1) return INTEGER is
variable x: vector;
variable p: INTEGER;
begin

    for l in 0 to NR_BITI - 2 loop
        if Q(l) = '1' then
            x(l) := 1;
        else x(l) := 0;
        end if;
    end loop;
    
    p := x(0) + x(1)*2 + x(2)*4 + x(3)*8 + x(4)*16 + x(5)*32 + x(6)*64 + x(7)*128 + x(8)*256 + x(9)*512;
    
    return p / 10 mod 10;
    
end function;

--functia CIFRA_U returneaza cifra unitatilor ca intreg dintr-un sir de biti transmis ca parametru
function CIFRA_U (Q: vector_1) return INTEGER is
variable x: vector;
variable p: INTEGER := 1;
begin

    for l in 0 to NR_BITI - 2 loop
        if Q(l) = '1' then
            x(l) := 1;
        else x(l) := 0;
        end if;
    end loop;
    
    p := x(0) + x(1)*2 + x(2)*4 + x(3)*8 + x(4)*16 + x(5)*32 + x(6)*64 + x(7)*128 + x(8)*256 + x(9)*512;
    
    return p mod 10;
    
end function;

--functia CIFRA_S returneaza cifra sutelor ca intreg dintr-un sir de biti transmis ca parametru
function CIFRA_S (Q: vector_1) return INTEGER is
variable x: vector;
variable p: INTEGER;
begin

    for l in 0 to NR_BITI - 2 loop
        if Q(l) = '1' then
            x(l) := 1;
        else x(l) := 0;
        end if;
    end loop;
    
    p := x(0) + x(1)*2 + x(2)*4 + x(3)*8 + x(4)*16 + x(5)*32 + x(6)*64 + x(7)*128 + x(8)*256 + x(9)*512;
    
    return p / 100 mod 10;
    
end function;


-- functia COMP compara 2 numere transmise ca parametru printr-un sir de biti si returneaza: "00" daca A > B; "01" daca A < B; "10" daca A = B
function COMP (A, B: vector_1) return vector_2 is
variable p, q: INTEGER;
variable x: vector;
begin

    for l in 0 to NR_BITI - 2 loop
        if A(l) = '1' then x(l) := 1; else x(l) := 0; end if;
    end loop;
    
    p := x(0) + x(1)*2 + x(2)*4 + x(3)*8 + x(4)*16 + x(5)*32 + x(6)*64 + x(7)*128 + x(8)*256 + x(9)*512;         -- A
    
    for l in 0 to NR_BITI - 2 loop
        if B(l) = '1' then x(l) := 1; else x(l) := 0; end if;
    end loop;
    
    q := x(0) + x(1)*2 + x(2)*4 + x(3)*8 + x(4)*16 + x(5)*32 + x(6)*64 + x(7)*128 + x(8)*256 + x(9)*512;         -- B
    
    if p > q then return "00";
        elsif p < q then return "01";
        elsif p = q then return "10";
    end if;
    
end function;


--functia COMPLEMENT_2 returneaza complementul fata de 2 ca sir de biti al unui sir de biti transmis ca parametru
function COMPLEMENT_2 (X: vector_1) return vector_1 is
variable K, Cin, B, U: vector_1;
begin

    for l in 0 to NR_BITI - 2 loop
        K(l) := not X(l);
    end loop;
   
    for l in NR_BITI - 1 downto 1 loop
        U(l) := '0';
    end loop;
    
    U(0) := '1';
    
    Cin(0) := '0';
                                        
        for l in 0 to NR_BITI - 2 loop                                                   
                                            
            if l = 0 then
                B(0) := K(0) xor U(0) xor '0';
                Cin(0) := K(0) and U(0);
            else
                B(l) := K(l) xor U(l) xor Cin(l - 1);
                Cin(l) := (Cin(l - 1) and K(l)) or (Cin(l - 1) and U(l)) or (K(l) and U(l));
            end if; 
                                            
        end loop;
    
    return B;
   
end function;


-- functia ADUNARE returneaza rezultatul adunarii cu semn sub forma de sir de biti al doua siruri de biti transmisi ca parametri
function ADUNARE (A, B: vector_1) return vector_1 is
variable R, Cin, AA, BB: vector_1;
-- R = rezultatul; Cin = carry; 
begin
     
     R := "00000000000";
     
     if A(NR_BITI - 1) = '0' and B(NR_BITI - 1) = '1' then
                if COMP(A, B) = "00" then  
                    AA := A;
                    BB := COMPLEMENT_2(B);
                    R(NR_BITI - 1) := '0';
                else
                    AA := COMPLEMENT_2(A);
                    BB := B;
                    R(NR_BITI - 1) := '1';
                end if;
        end if;
                
                
            if A(NR_BITI - 1) = '1' and B(NR_BITI - 1) = '0' then
                if COMP(A, B) = "00" then
                    AA := A;
                    BB := COMPLEMENT_2(B);
                    R(NR_BITI - 1) := '1';
                elsif COMP(A, B) = "00" then
                    AA := COMPLEMENT_2(A);
                    BB := B;
                    R(NR_BITI - 1) := '0';
                end if;
            end if;            
             
             
            if A(NR_BITI - 1) = '0' and B(NR_BITI - 1) = '0' then
                AA := A;
                BB := B;
                R(NR_BITI - 1) := '0';
            end if;
            
            
            if A(NR_BITI - 1) = '1' and B(NR_BITI - 1) = '1'then
                AA := A;
                BB := B;
                R(NR_BITI - 1) := '1';
            end if;
           
     
     
    Cin(0) := '0';     
                                    
    for l in 0 to NR_BITI - 2 loop                                                   
                                        
        if l = 0 then
            R(0) := AA(0) xor BB(0) xor '0';
            Cin(0) := AA(0) and BB(0);
        else
            R(l) := AA(l) xor BB(l) xor Cin(l - 1);
            Cin(l) := (Cin(l - 1) and AA(l)) or (Cin(l - 1) and BB(l)) or (AA(l) and BB(l));
        end if; 
                                        
    end loop;
        
    return R;                                 
                                
end function;


-- functia ADUNARE_2 returneaza rezultatul adunarii fara semn sub forma de sir de biti al doua siruri de biti 
function ADUNARE_2 (A, B: vector_1) return vector_1 is
variable R, C: vector_1;
begin

    C := "00000000000";
    R := "00000000000";
    
    for i in 0 to NR_BITI - 2 loop
        if i = 0 then
            R(0) := A(0) xor B(0) xor '0';
            C(0) := A(0) and B(0);
        else
            R(i) := A(i) xor B(i) xor C(i - 1);
            C(i) := (C(i - 1) and A(i)) or (C(i - 1) and B(i)) or (A(i) and B(i));
        end if;
    end loop;
    
    return R;

end function;


--functia NUMAR returneaza numarul ca intreg al unui sir de biti 
function NUMAR (A: vector_1) return INTEGER is
variable x: INTEGER;
variable p: vector;
begin

    for i in 0 to NR_BITI - 2 loop
        if A(i) = '1' then p(i) := 1; else p(i) := 0; end if;
    end loop;
    
    x := p(0) + p(1)*2 + p(2)*4 + p(3)*8 + p(4)*16 + p(5)*32 + p(6)*64 + p(7)*128;
    
    return x;

end function;



-- functia INMULTIRE_2 returneaza rezultatul inmultirii  sub forma de sir de biti a doua numere transmise ca parametru sir de biti
function INMULTIRE_2 (A, B: vector_1) return vector_1 is
variable x: INTEGER;
variable R, P: vector_1;
begin

    R := A;
    
    for i in 0 to NR_BITI - 2 loop
        P(i) := B(i);
    end loop;
    
    P(NR_BITI - 1) := '0';
    
    x := NUMAR(P) - 1;

    BUCLA: for i in 0 to 31 loop
    
        if x = 0 then exit BUCLA;
            end if;
            
        R := ADUNARE_2(R, A);
        
        
--        P := ADUNARE(P, "10000000001");
        
--        if P = "0000000000" then exit BUCLA;
--        end if;

        
        x := x - 1;
        
        end loop;
        
        R(NR_BITI - 1) := A(NR_BITI - 1) xor B(NR_BITI - 1);
        
        return R;

end function;



-- functia IMPARTIRE returneaza rezultatul impartirii a 2 numere sub forma de biti daca semnalul C = '1' sau returneaza restul impartirii a doua numere daxa C = '0'
function IMPARTIRE (A, B: vector_1; C: BIT) return vector_1 is
variable CC, R, AA, BB, REST: vector_1;
variable O: vector_2;
begin

    for i in 0 to NR_BITI - 2 loop
        BB(i) := B(i);
        AA(i) := A(i);
        CC(i) := A(i);
    end loop;
    
    
    CC(NR_BITI - 1) := '1';
    BB(NR_BITI - 1) := '1';
    AA(NR_BITI - 1) := '0';
    
    R := ADUNARE(AA, CC);

    BUCLA: for i in 0 to 63 loop
    
                    O := COMP(AA, BB);
                                     
                    if O = "01" then
                        exit BUCLA;
                    end if;
                    
                    AA := ADUNARE(AA, BB);
                                
                    R(0) := R(0) xor '1';
                    R(1) := R(1) xor not R(0);
                    R(2) := R(2) xor (not R(1) and not R(0));
                    R(3) := R(3) xor (not R(2) and not R(1) and not R(0));
                    R(4) := R(4) xor (not R(3) and not R(2) and not R(1) and not R(0));
                    R(5) := R(5) xor (not R(4) and not R(3) and not R(2) and not R(1) and not R(0));
                    R(6) := R(6) xor (not R(5) and not R(4) and not R(3) and not R(2) and not R(1) and not R(0));

            end loop;
            
    R(NR_BITI - 1) := A(NR_BITI - 1) xor B(NR_BITI - 1);
            
    REST := AA;
    
    
    if C = '1' then 
        return R;
    else 
        return REST;
    end if;

end function;

--inceputul arhitecturii

-- BUTTON_5_1, BUTTON_1_1, BUTTON_3_1, BUTTON_2_1 = semnalele utilizate dupa debounce 
-- DDD, DD, D, DEB, DEB_B, DEB_C, DEB_D = semnale ajutatoare utilizate la debounce
-- OK = semnal utilizat ca si tact de ceas pentru cele 3 etape ale impartirii
-- K = semnal pentru numaratorul de stari din proiect
-- L, A, B, Cin, R, J, H, AA, BB, P = semnale ajutatoare in efectuarea operatiilor

signal OK_1, OK_2, OK_3, OK_4, RES, Cout, SEMN, S, D, BUTTON_5_1, BUTTON_1_1, BUTTON_3_1, DD, BUTTON_2_1, DDD, E, F : BIT;
signal DEB, DEB_B, DEB_C, DEB_D: BIT_VECTOR (2 downto 0);
signal K, OK: BIT_VECTOR (1 downto 0);
signal L, A, B, Cin, R, J, H, AA, BB, P: vector_1;

begin           
    --proces pentru divizarea tactului si debounce la butoane
    
    process (CLK, BUTTON_1, BUTTON_5, BUTTON_2, BUTTON_3)                                   
    variable x, y, n, p, q: INTEGER;
    begin
   
        if CLK'EVENT and CLK = '1' then
            x := x + 1;
            y := y + 1;
            n := n + 1;
            p := p + 1;
            q := q + 1;
        end if;
        
        -- activarea pe rand a afisoarelor; OK_1 -> afisorul 1; OK_2 -> afisorul 2; OK_3 -> afisorul 3; OK_4 -> afisorul 4
        if (x > 0) and (x < 200_000) then                               
            OK_1 <= '1';                                                
            OK_2 <= '0';                                                
            OK_3 <= '0';                                                
            OK_4 <= '0';                                                
        end if;
       
        if (x > 200_000) and (x < 400_000) then
            OK_1 <= '0';
            OK_2 <= '1';
            OK_3 <= '0';
            OK_4 <= '0';
        end if;
        
        if (x > 400_000) and (x < 600_000) then
            OK_1 <= '0';
            OK_2 <= '0';
            OK_3 <= '1';
            OK_4 <= '0';
        end if;
        
        if (x > 600_000) and (x < 800_000) then
            OK_1 <= '0';
            OK_2 <= '0';
            OK_3 <= '0';
            OK_4 <= '1';
        end if;
                
        if x = 800_000 then
            x := 0;
        end if;
        
        -- OK -> semnalul de tact pentru afisarea celor 3 etape ale impartirii: afisare rezultat; afisare mesaj "rest"; afisare rest
        if (y < 50_000_000) then
            OK <= "00";
        end if;
                
       if (y > 50_000_000) and (y < 100_000_000) then
            OK <= "01";
       end if;
                
        if y >100_000_000 and y < 150_000_000 then
            OK <= "10";
        end if;
                
        if y = 150_000_000 then
            y := 0;
        end if; 
        
        -- debounce la butoanele utilizate in proiect
        if (n = 800) or (n = 1600) or (n = 2400) then
            DEB_B(2) <= DEB_B(1);
            DEB_B(1) <= DEB_B(0);
            DEB_B(0) <= BUTTON_5;
        end if;
        
        if (p = 800) or (p = 1600) or (p = 2400) then
            DEB_C(2) <= DEB_C(1);
            DEB_C(1) <= DEB_C(0);
            DEB_C(0) <= BUTTON_1;
        end if;
        
        if (q = 800) or (q = 1600) or (q = 2400) then
            DEB_D(2) <= DEB_D(1);
            DEB_D(1) <= DEB_D(0);
            DEB_D(0) <= BUTTON_2;
        end if;
                
        if q = 2401 then
            q := 0;
        end if;
                
        if p = 2401 then
            p := 0;
        end if;
                
        if n = 2401 then
            n := 0;
        end if;
                
        DD <= DEB_C(0) and DEB_C(1) and DEB_C(2);
        DDD <= DEB_D(0) and DEB_D(1) and DEB_D(2);
        D <= DEB_B(0) and DEB_B(1) and DEB_B(2);
                
        if DDD = '1' then 
            BUTTON_2_1 <= '1';
        else BUTTON_2_1 <= '0';
        end if;
        
        if DD = '1' then
            BUTTON_1_1 <= '1';
        else BUTTON_1_1 <= '0';
        end if;
        
        if D = '1' then
            BUTTON_5_1 <= '1';
        else BUTTON_5_1 <= '0';
        end if;
                 
    end process;
    
        --proces pentru incarcarea numerelor + afisare pe afisor
        
       process (BUTTON_1_1, BUTTON_2, BUTTON_5_1, CLK, SEMN, BUTTON_3, BUTTON_2_1)                                                  
       variable c_z, c_u, c_s: INTEGER range 0 to 9;
       variable y, W: INTEGER;
       variable AA, BB, P: vector_1;
       begin
       
        -- numarator pentru trecerea dintr-o stare in alta a proiectului (citirea numerelor, alegerea operatiilor, afisarea rezultatului)                              
          if BUTTON_5_1'EVENT and BUTTON_5_1 = '1' then
                Q(0) <= Q(0) xor '1';
                Q(1) <= Q(1) xor Q(0);
                Q(2) <= Q(2) xor (Q(1) and Q(0));
          end if;
                    
          RES <= '0';

-- pasul 2 -> citirea primului numar
                                      
          if Q = "001" then    
          
                E <= '0';
          -- numarator pentru alegerea semnului primului numar
                if BUTTON_3'EVENT and BUTTON_3 = '1' then
                    SEMN <= SEMN xor '1';
                end if;                                                             
            
                A(NR_BITI - 1) <= SEMN;
          -- numarator bidirectional pentru alegerea primului numar
                if ((BUTTON_1_1'EVENT) and (BUTTON_1_1 = '1')) then
                    if SEL = '0' then
                        A(0) <= A(0) xor '1';
                        A(1) <= A(1) xor A(0);
                        A(2) <= A(2) xor (A(1) and A(0));
                        A(3) <= A(3) xor (A(2) and A(1) and A(0));
                        A(4) <= A(4) xor (A(3) and A(2) and A(1) and A(0));
                        A(5) <= A(5) xor (A(4) and A(3) and A(2) and A(1) and A(0));
                        A(6) <= A(6) xor (A(5) and A(4) and A(3) and A(2) and A(1) and A(0));
                     else
                        A(0) <= A(0) xor '1';
                        A(1) <= A(1) xor (not A(0));
                        A(2) <= A(2) xor ((not A(1)) and (not A(0)));
                        A(3) <= A(3) xor ((not A(2)) and (not A(1)) and (not A(0)));
                        A(4) <= A(4) xor ((not A(3)) and (not A(2)) and (not A(1)) and (not A(0)));
                        A(5) <= A(5) xor ((not A(4)) and (not A(3)) and (not A(2)) and (not A(1)) and (not A(0)));
                        A(6) <= A(6) xor ((not A(5)) and (not A(4)) and (not A(3)) and (not A(2)) and (not A(1)) and (not A(0)));
                    end if;
            end if;
         -- repartizrea cifrelor numarului 2 pe cifre pentru afisarea de pe afisor              
            c_z := CIFRA_Z(A);      c_u := CIFRA_U(A);      c_s := CIFRA_S(A);
            
        end if;
        
-- pasul 3 -> citirea numarului 2             
        if Q = "011" then                                                               
        
            B(NR_BITI - 1) <= SEMN;
         -- numarator pentru alegerea semnului numarului
            if BUTTON_3'EVENT and BUTTON_3 = '1' then
                SEMN <= SEMN xor '1';
            end if;
         -- numarator bidirectional pentru alegerea numarului
            if ((BUTTON_1'EVENT) and (BUTTON_1 = '1')) then
                        if SEL = '0' then
                            B(0) <= B(0) xor '1';
                            B(1) <= B(1) xor B(0);
                            B(2) <= B(2) xor (B(1) and B(0));
                            B(3) <= B(3) xor (B(2) and B(1) and B(0));
                            B(4) <= B(4) xor (B(3) and B(2) and B(1) and B(0));
                            B(5) <= B(5) xor (B(4) and B(3) and B(2) and B(1) and B(0));
                            B(6) <= B(6) xor (B(5) and B(4) and B(3) and B(2) and B(1) and B(0));
                         else
                            B(0) <= B(0) xor '1';
                            B(1) <= B(1) xor (not B(0));
                            B(2) <= B(2) xor ((not B(1)) and (not B(0)));
                            B(3) <= B(3) xor ((not B(2)) and (not B(1)) and (not B(0)));
                            B(4) <= B(4) xor ((not B(3)) and (not B(2)) and (not B(1)) and (not B(0)));
                            B(5) <= B(5) xor ((not B(4)) and (not B(3)) and (not B(2)) and (not B(1)) and (not B(0)));
                            B(6) <= B(6) xor ((not B(5)) and (not B(4)) and (not B(3)) and (not B(2)) and (not B(1)) and (not B(0)));
                        end if;
                    end if;
          -- repartizrea cifrelor numarului 2 pe cifre pentru afisarea de pe afisor            
            c_z := CIFRA_Z(B);      c_u := CIFRA_U(B);      c_s := CIFRA_S(B);
            
        end if;
        
-- pasul 4 -> alegerea operatiilor (1 = adunare; 2 = scadere; 3 = inmultire; 4 = impartire)               
        if Q = "100" then                                               
        
            SEMN <= '0';
        --numarator pentru alegerea operatiilor                
            if ((BUTTON_2'EVENT) and (BUTTON_2 = '1')) then              
                    K(0) <= K(0) xor '1';
                    K(1) <= K(1) xor K(0);
                end if;
                
        -- afisarea operatiilor disponibile ("OP 1/2/3/4")
            if OK_1 = '1' then                                                              
                                    ANOD <= "1110";
                                    
                                    case K is
                                        when "00" => CATOD <= "1001111";
                                        when "01" => CATOD <= "0010010";
                                        when "10" => CATOD <= "0000110";
                                        when "11" => CATOD <= "1001100";
                                    end case;
                                    
                                end if;
                                
                                if OK_2 = '1' then
                                    ANOD <= "1111";
                                end if;
                                
                                if OK_3 = '1' then
                                    ANOD <= "1011";
                                    CATOD <= "0011000";
                                end if;
                                
                                if OK_4 = '1' then
                                    ANOD <= "0111";
                                    CATOD <= "0000001";                        
                                end if;         
            end if;
            
-- pasul 6 -> efectuarea operatiei alese si afisarea rezultatului                        
        if Q = "101" then                        
        
            case K is
            -- 00 = adunare
                when "00" => R <= ADUNARE(A, B);
            -- 01 = diferenta                                
                when "01" =>       
                                                                              
                    for i in 0 to NR_BITI - 2 loop
                        P(i) := B(i);
                    end loop; 
                    
                    if B(NR_BITI - 1) = '1' then P(NR_BITI - 1) := '0';
                    elsif B(NR_BITI - 1) = '0' then P(NR_BITI - 1) := '1';
                    end if;
                       
                    R <= ADUNARE(A, P);
              -- 10 = inmultire       
                when "10" =>                                                       
                
                    if NUMAR(A) = 0 or NUMAR(B) = 0 then 
                        R <= "00000000000";
                    else
                        R <= INMULTIRE_2(A, B);
                    end if;  
                    
                when others => null;      
                    
            end case;
         -- repartizarea rezultatului pe cifre pentru afisare   
            c_z := CIFRA_Z(R);
            c_u := CIFRA_U(R);
            c_s := CIFRA_S(R);
        end if;
-- pasul 6 -> resetarea tuturor elementelor                
        if Q = "110" then
            A <= "00000000000";
            B <= "00000000000";
            R <= "00000000000";
            K <= "00";
            SEMN <= '0';
            RES <= '1';
        end if;
                        
        if RES = '1' then
            Q <= "000";
        end if;            
-- pasul 1 -> afisarea mesajului "nr 1"            
    if Q = "000" then
                    
                    if OK_1 = '1' then
                        ANOD <= "1110";
                        CATOD <= "1001111";
                    end if;
                    
                    if OK_2 = '1' then
                        ANOD <= "1111";
                    end if;
                    
                    if OK_3 = '1' then
                        ANOD <= "1011";
                        CATOD <= "1111010";
                    end if;
                    
                    if OK_4 = '1' then
                        ANOD <= "0111";
                        CATOD <= "1101010";
                    end if;
    end if;                      

-- afisarea mesajului "nr 2"                                        
    if Q = "010" then              
                    
                    if OK_1 = '1' then
                        ANOD <= "1110";
                        CATOD <= "0010010";
                    end if;
                    
                    if OK_2 = '1' then
                        ANOD <= "1111";
                    end if;
                    
                    if OK_3 = '1' then
                        ANOD <= "1011";
                        CATOD <= "1111010";
                    end if;
                    
                    if OK_4 = '1' then
                        ANOD <= "0111";
                        CATOD <= "1101010";
                    end if;
    end if;

-- impartirea
    if K = "11" and Q = "101" then
    
    
        if NUMAR(A) = 0 or NUMAR(B) = 0 then
        -- verificarea cazului in care primul numar este 0                
                        if NUMAR(A) = 0 then
                            R <= "00000000000";
                            ANOD <= "1110";
                            CATOD <= "0000001";
                        end if;
        -- verificarea cazului in care al doilea numar este 0 si afisarea mesajului "Err"                        
                        if NUMAR(B) = 0 then                                       
                            
                            if OK_1 = '1' then
                                ANOD <= "0111";
                                CATOD <= "0110000";
                            end if;
                            
                            if OK_2 = '1' then
                                ANOD <= "1011";
                                CATOD <= "1111010";
                            end if;
                            
                            if OK_3 = '1' then
                                ANOD <= "1101";
                                CATOD <= "1111010";
                            end if;
                            
                            if OK_4 = '1' then 
                                ANOD <= "1111";
                            end if;     
                        end if;
                        
                        else
               -- afisarea rezultatului in 3 etape la diferenta de 1/2 s: 1 = rezultatul impartirii; 2 = afisarea mesajului "rest"; 3 = restul impartirii
                            case OK is
                            
                                when "00" => 
                                -- etapa 1 -> afisarea rezultatului
                                            if NUMAR(B) = 1 then
                                                R <= A;
                                            else
                                                R <= IMPARTIRE(A, B, '1');
                                            end if;                                            
                                           --R(NR_BITI - 1) <= A(NR_BITI - 1) xor B(NR_BITI - 1);
                                    
                                when "10" => 
                                -- etapa 3 -> afisarea restului
                                            if NUMAR(B) = 1 then
                                                R <= "00000000000";
                                            else
                                                R <= IMPARTIRE(A, B, '0');
                                            end if;
                                    
                                when others => null;
                                
                            end case;
    
        -- etapa 3 -> afisarea mesajului "rest"
        if OK = "01" then
                                                                                   
                                        if OK_1 = '1' then
                                            ANOD <= "1110";
                                            CATOD <= "1110000";
                                        end if;
                                        
                                        if OK_2 = '1' then
                                            ANOD <= "1101";
                                            CATOD <= "0100100";
                                        end if;
                                        
                                        if OK_3 = '1' then
                                            ANOD <= "1011";
                                            CATOD <= "0110000";
                                        end if;
                                        
                                        if OK_4 = '1' then
                                            ANOD <= "0111";
                                            CATOD <= "1111010";
                                        end if;
                                        
         else
         -- afisarea pe afisor a rezultatului impartirii
         -- OK_1 = semnalul care activeaza al patrulea afisor timp de 3 ms
            if OK_1 = '1' then
                     
                         ANOD <= "1110";
                    -- afisarea cifrei unitatilor
                         case c_u is                                                 
                                             when 0 => CATOD <= "0000001"; 
                                             when 1 => CATOD <= "1001111";
                                             when 2 => CATOD <= "0010010";
                                             when 3 => CATOD <= "0000110";
                                             when 4 => CATOD <= "1001100";
                                             when 5 => CATOD <= "0100100";
                                             when 6 => CATOD <= "0100000";
                                             when 7 => CATOD <= "0001111";
                                             when 8 => CATOD <= "0000000";
                                             WHEN 9 => CATOD <= "0000100";
                                             when others => CATOD <= "1111111";
                                         end case;
                         
                         
                     end if;
         
                     
              -- OK_2 = semnalul care activeaza afisorul al treilea timp de 3 ms       
                     if OK_2 = '1' then
                         
                  -- afisarea semnului "-" pe al treilea afisor in cazul in care primul numar e mai mic decat 0 si este format dintr-o cifra
                  -- verificarea cazului in care cifra zecilor sau ciffra sutelor este 0, caz in care afisoarele pentru cifrele respective vor fi stinse       
                         if (c_z = 0) and (c_u = 0) and (c_s = 0) then
                             ANOD <= "1111";
                         else
                         
                         if (c_z = 0) and (c_s = 0) then
                             if SEMN = '1' or R(NR_BITI - 1) = '1' then
                                 ANOD <= "1101";
                                 CATOD <= "1111110";                                             
                             else 
                                 ANOD <= "1111";
                             end if;
                         end if;
                         
                         end if;
                         
                     
                         if (c_z = 0) and (c_s > 0) then
                             ANOD <= "1101";
                             CATOD <= "0000001";
                         end if;
                         
                         if c_z > 0 then
                         
                         ANOD <= "1101";
                         
                         case c_z is                                                             --cifra zecilor   
                             when 1 => CATOD <= "1001111";  
                             when 2 => CATOD <= "0010010";  
                             when 3 => CATOD <= "0000110";  
                             when 4 => CATOD <= "1001100";   
                             when 5 => CATOD <= "0100100";  
                             when 6 => CATOD <= "0100000";  
                             when 7 => CATOD <= "0001111";  
                             when 8 => CATOD <= "0000000";  
                             WHEN 9 => CATOD <= "0000100"; 
                             when others => CATOD <= "1111111";
                         end case;
                         end if;            
                     
                     end if;
                     
                     
             -- OK_3 = semnalul care activeaza afisorul al doilea timp de 3 ms        
                     if OK_3 = '1' then
                  -- verificarea conditiilor de la pasul anterior   
                         if c_s = 0 and c_z > 0 then
                         
                             if SEMN = '1' or R(NR_BITI - 1) = '1' then
                                 ANOD <= "1011";
                                 CATOD <= "1111110";
                             else
                                 ANOD <= "1111";
                             end if;
                             
                         elsif c_s = 0 and c_z = 0 then
                             ANOD <= "1111";
                         end if;
                    
                             
                         if c_s > 0 then
                             ANOD <= "1011";
                             case c_s is
                                 when 1 => CATOD <= "1001111";
                                 when 2 => CATOD <= "0010010";
                                 when 3 => CATOD <= "0000110";
                                 when 4 => CATOD <= "1001100";                                           --cifra sutelor
                                 when 5 => CATOD <= "0100100";
                                 when 6 => CATOD <= "0100000";
                                 when 7 => CATOD <= "0001111";
                                 when 8 => CATOD <= "0000000";
                                 when 9 => CATOD <= "0000100";
                                 when others => CATOD <= "1111111";
                             end case;
                             
                         end if;
                     
                     end if;
                     
                     
             -- OK_4 = semnalul care activeaza primul afisor timp de 3 ms        
                     if OK_4 = '1' then
         
                        -- pe acest afisor se va afisa semnul "-" daca numarul este mai mic decat 0 sau nu se va afisa nimic in caz contrar
                         if SEMN = '0' and R(NR_BITI - 1) = '0' then
                             ANOD <= "1111";
                         elsif ((SEMN = '1') or (R(NR_BITI - 1) = '1')) and c_s > 0 then                                                   --semnul
                             ANOD <= "0111";
                             CATOD <= "1111110";
                         end if;
                                     
                     end if;
            end if;
            end if;
         
    else
        
-- afisarea rezultatului operatiilor de adunare, scadere si inmultire sau afisarea numarului in timpul in care il alegem
    if (Q = "001") or (Q = "011") or (Q = "101") then                           
    
        -- afisarea cifrei unitatilor
            if OK_1 = '1' then
            
                ANOD <= "1110";
                
                case c_u is                                                
                                    when 0 => CATOD <= "0000001"; 
                                    when 1 => CATOD <= "1001111";
                                    when 2 => CATOD <= "0010010";
                                    when 3 => CATOD <= "0000110";
                                    when 4 => CATOD <= "1001100";
                                    when 5 => CATOD <= "0100100";
                                    when 6 => CATOD <= "0100000";
                                    when 7 => CATOD <= "0001111";
                                    when 8 => CATOD <= "0000000";
                                    WHEN 9 => CATOD <= "0000100";
                                    when others => CATOD <= "1111111";
                                end case;
                
                
            end if;

            
            -- afisarea cifrei zecilor + cazurile afisarii de la impartire
            if OK_2 = '1' then
                
                
                if (c_z = 0) and (c_u = 0) and (c_s = 0) then
                    ANOD <= "1111";
                else
                
                if (c_z = 0) and (c_s = 0) then
                    if SEMN = '1' or R(NR_BITI - 1) = '1' then
                        ANOD <= "1101";
                        CATOD <= "1111110";                                             
                    else 
                        ANOD <= "1111";
                    end if;
                end if;
                
                end if;
                
                
                if (c_z = 0) and (c_s > 0) then
                    ANOD <= "1101";
                    CATOD <= "0000001";
                end if;
                
                if c_z > 0 then
                
                ANOD <= "1101";
                
                case c_z is                                                             --cifra zecilor   
                    when 1 => CATOD <= "1001111";  
                    when 2 => CATOD <= "0010010";  
                    when 3 => CATOD <= "0000110";  
                    when 4 => CATOD <= "1001100";   
                    when 5 => CATOD <= "0100100";  
                    when 6 => CATOD <= "0100000";  
                    when 7 => CATOD <= "0001111";  
                    when 8 => CATOD <= "0000000";  
                    WHEN 9 => CATOD <= "0000100"; 
                    when others => CATOD <= "1111111";
                end case;
                end if;            
            
            end if;
            
            
          -- afisarea cifrei sutelor + cazurile afisarii de la impartire  
            if OK_3 = '1' then
            
                if c_s = 0 and c_z > 0 then
                
                    if SEMN = '1' or R(NR_BITI - 1) = '1' then
                        ANOD <= "1011";
                        CATOD <= "1111110";
                    else
                        ANOD <= "1111";
                    end if;
                    
                elsif c_s = 0 and c_z = 0 then
                    ANOD <= "1111";
                end if;
           
                    
                if c_s > 0 then
                    ANOD <= "1011";
                    case c_s is
                        when 1 => CATOD <= "1001111";
                        when 2 => CATOD <= "0010010";
                        when 3 => CATOD <= "0000110";
                        when 4 => CATOD <= "1001100";                                           --cifra sutelor
                        when 5 => CATOD <= "0100100";
                        when 6 => CATOD <= "0100000";
                        when 7 => CATOD <= "0001111";
                        when 8 => CATOD <= "0000000";
                        when 9 => CATOD <= "0000100";
                        when others => CATOD <= "1111111";
                    end case;
                    
                end if;
            
            end if;
            
            
         -- afisarea semnului "-" sau nimic   
            if OK_4 = '1' then

                if SEMN = '0' and R(NR_BITI - 1) = '0' then
                    ANOD <= "1111";
                elsif ((SEMN = '1') or (R(NR_BITI - 1) = '1')) and c_s > 0 then                                                   --semnul
                    ANOD <= "0111";
                    CATOD <= "1111110";
                end if;
                            
            end if;        
               
            
    end if;                
            
    end if;
    
          end process;
                
              

end Behavioral;
