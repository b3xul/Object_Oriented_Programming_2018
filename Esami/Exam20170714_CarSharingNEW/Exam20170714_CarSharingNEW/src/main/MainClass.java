package main;

import carShare.CarService;
import carShare.InvalidName;

public class MainClass {

	public static void main(String[] args) {
		CarService cs = new CarService();
		try {
			cs.addParking("PortaNuova");
			cs.addParking("PortaSusa");
			cs.addParking("Politecnico");
			cs.addCar("PortaNuova", "A2", 0.2, 0.25);
			cs.addCar("PortaNuova", "A3", 0.2, 0.25);
			cs.addCar("PortaNuova", "A1", 0.25, 0.3);
			cs.addCar("PortaSusa", "A5", 0.25, 0.3);
			cs.addCar("PortaSusa", "A4", 0.2, 0.2);
			System.out.println("1) PortaNuova - disponibili: " + cs.getAvailableCars("PortaNuova"));
			// 1) PortaNuova - disponibili: [A1, A2, A3]
			System.out.println("2) PortaSusa - disponibili: " + cs.getAvailableCars("PortaSusa"));
			// 2) PortaSusa - disponibili: [A4, A5]
			System.out.println("3) Politecnico - disponibili : " + cs.getAvailableCars("Politecnico"));
			// 3) Politecnico - disponibili : []
			cs.addSubscriber("Z789");
			cs.addSubscriber("W000");
			cs.addSubscriber("X123");
			cs.addSubscriber("Y456");
			System.out.println("4) abbonati: " +cs.getSubscribers());
			// 4) abbonati: [W000, X123, Y456, Z789]
			String carPlate = cs.reserve("X123", "PortaNuova");
			System.out.println("5) prenotata a PortaNuova: "+carPlate);
			// 5) prenotata a PortaNuova: A1
			System.out.println("6) PortaNuova - disponibili: "+cs.getAvailableCars("PortaNuova")+", prenotate: "+cs.getReserved("PortaNuova"));
			// 6) PortaNuova - disponibili: [A2, A3], prenotate: [A1]
			cs.useCar("X123", carPlate);
			System.out.println("7) PortaNuova - disponibili: "+cs.getAvailableCars("PortaNuova")+", prenotate: "+cs.getReserved("PortaNuova"));
			// 7) PortaNuova - disponibili: [A2, A3], prenotate: []
			System.out.println("8) nota addebito: " + cs.terminate("X123", carPlate, "Politecnico", 110, 46));
			// 8) nota addebito: X123:A1:41.3:PortaNuova:Politecnico
			System.out.println("9) Politecnico - disponibili: " + cs.getAvailableCars("Politecnico")+", prenotate: "+cs.getReserved("Politecnico"));
			// 9) Politecnico - disponibili: [A1], prenotate: []
			carPlate = cs.reserve("X123", "Politecnico");
			System.out.println("10) prenotata a Politecnico: "+carPlate);
			// 10) prenotata a Politecnico: A1
			System.out.println("11) Politecnico - disponibili: " + cs.getAvailableCars("Politecnico")+", prenotate: "+cs.getReserved("Politecnico"));
			// 11) Politecnico - disponibili: [], prenotate: [A1]
			String carPlate1 = cs.reserve("X123", "PortaNuova");
			System.out.println("12) prenotata a PortaNuova: "+carPlate1);
			// 12) prenotata a PortaNuova: null
			System.out.println("13) Politecnico - disponibili: " + cs.getAvailableCars("Politecnico")+", prenotate: "+cs.getReserved("Politecnico"));
			// 13) Politecnico - disponibili: [], prenotate: [A1]
			cs.useCar("X123", carPlate);
			cs.terminate("X123", carPlate, "PortaSusa", 85, 32);
			carPlate = cs.reserve("Y456", "PortaSusa");
			System.out.println("14) prenotata a PortaSusa: "+carPlate);
			// 14) prenotata a PortaSusa: A1
			System.out.println("15) annullata prenotazione: "+cs.release("Y456", carPlate));
			// 15) annullata prenotazione: A1
			carPlate = cs.reserve("Y456", "PortaNuova");
			System.out.println("16) prenotata a PortaNuova: "+carPlate);
			// 16) prenotata a PortaNuova: A2
			cs.useCar("Y456", carPlate);
			cs.terminate("Y456", carPlate, "PortaSusa", 143, 68);
			carPlate = cs.reserve("Z789", "PortaSusa");			
			System.out.println("17) prenotata a PortaSusa: "+carPlate);
			// 17) prenotata a PortaSusa: A1
			cs.useCar("Z789", carPlate);
			cs.terminate("Z789", carPlate, "PortaNuova", 57, 25);
			System.out.println("18) note addebito: " + cs.charges());
			// 18) note addebito: [Y456:A2:45.6:PortaNuova:PortaSusa, X123:A1:41.3:PortaNuova:Politecnico, X123:A1:30.85:Politecnico:PortaSusa, Z789:A1:21.75:PortaSusa:PortaNuova]
			System.out.println("19) addebiti X123: " + cs.subscriberCharges("X123"));		
			// 19) addebiti X123: [X123:A1:41.3:PortaNuova:Politecnico, X123:A1:30.85:Politecnico:PortaSusa]
			System.out.println("20) addebito medio: " + cs.averageCharge());
			// 20) addebito medio: 34.875
			System.out.println("21) partenze da PortaNuova: " + cs.departuresFrom("PortaNuova"));
			// 21) partenze da PortaNuova: 2
		}
		catch (InvalidName e) {
			e.printStackTrace();
		}

	}

}
