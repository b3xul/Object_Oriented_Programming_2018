package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestR1_Agenzia_Taxi.class, TestR2_R3_Luogo_Passeggero.class,
		TestR4_GestioneTaxi.class, TestR5_Corse.class, TestR6_Statistiche.class })
public class AllTests {

}
