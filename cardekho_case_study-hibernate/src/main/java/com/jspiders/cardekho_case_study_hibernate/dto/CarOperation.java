//Bhushan Chavan
package com.jspiders.cardekho_case_study_hibernate.dto;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.loader.GeneratedCollectionAliases;

import com.jspiders.cardekho_case_study_hibernate.dto.Car;

public class CarOperation {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;

	private static void openConnection() {
		entityManagerFactory = Persistence.createEntityManagerFactory("car");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
	}

	private static void closeConnection() {
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}

		if (entityManager != null) {
			entityManager.close();
		}

		if (entityTransaction != null) {
			if (entityTransaction.isActive()) {
				entityTransaction.rollback();
			}
		}
	}

	public static void addCars() {
		try {
			openConnection();
			entityTransaction.begin();
			System.out.println("===============================================================\n");
			System.out.println("How Many Cars You Want to Add?");
			Scanner scanner = new Scanner(System.in);
			int choice = scanner.nextInt();
			

			for (int i = 1; i <= choice; i++) {

				Car car=new Car();
				System.out.println("Enter the details for car " + i);

				System.out.println("\nEnter Car id: ");
				 car.setCar_id(scanner.nextInt());
				 
				 System.out.println("\nEnter Car Name: ");
				 car.setName(scanner.next());

				System.out.println("\nEnter Car Model: ");
				 car.setModel(scanner.next());

				System.out.println("\nEnter Car Brand: ");
				 car.setBrand(scanner.next());

				System.out.println("\nEnter Car Fuel Type: ");
				 car.setFuel_type(scanner.next());

				System.out.println("\nEnter Car Price: ");
				 car.setPrice(scanner.nextDouble());


				entityManager.persist(car);
				
				System.out.println("Car added.");
				
				
			}
			entityTransaction.commit();
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			closeConnection();
		}
	}

	public static void SearchByname() {
		try {
			openConnection();
			entityTransaction.begin();
			Scanner scanner = new Scanner(System.in);

			Query query = entityManager.createQuery("Select car from Car car where name in (?1)");
			System.out.println("Enter Car Name You want to Search : ");
			query.setParameter(1, scanner.next());
			List list = query.getResultList();
			for (Object object : list) {
				System.out.println(object);
			}

			entityTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}

	}

	public static void SearchByModel() {
		try {
			openConnection();
			entityTransaction.begin();

			Scanner scanner = new Scanner(System.in);

			Query query = entityManager.createQuery("Select car from Car car where model in (?1) ");
			System.out.println("Enter Car Model You want to Search : ");
			query.setParameter(1, scanner.next());
			List list = query.getResultList();
			for (Object object : list) {
				System.out.println(list);
			}
			entityTransaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	public static void SearchByBrand() {
		try {
			openConnection();
			entityTransaction.begin();
			Scanner scanner = new Scanner(System.in);

			Query query = entityManager.createQuery("Select car from Car car where brand in (?1)");
			System.out.println("Enter Car Brand You Want to Search : ");
			query.setParameter(1, scanner.next());
			List list = query.getResultList();

			for (Object object : list) {
				System.out.println(list);
			}
			entityTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	public static void SearchByFuelType() {
		try {
			openConnection();
			entityTransaction.begin();

			Scanner scanner = new Scanner(System.in);
			Query query = entityManager.createQuery("Select car from Car car where fuel_type in(?1)");
			System.out.println("Enter Fuel Type You want to Search : ");
			query.setParameter(1, scanner.next());

			List list = query.getResultList();

			for (Object object : list) {
				System.out.println(list);
			}
			entityTransaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	public void updateCar() {

		try {
			openConnection();
			entityTransaction.begin();
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter Car id You want to update: ");
			int id = scanner.nextInt();

			Car car = entityManager.find(Car.class, id);
			System.out.println("Enter updated car name:");
			String name = scanner.next();
			car.setName(name);
			System.out.println("Enter updated car brand:");
			String brand = scanner.next();
			car.setBrand(brand);
			System.out.println("Enter updated car model:");
			String model = scanner.next();
			car.setModel(model);
			System.out.println("Enter updated car fuel_type:");
			String fuel_type = scanner.next();
			car.setFuel_type(fuel_type);
			System.out.println("Enter updated car price:");
			Double price = scanner.nextDouble();
			car.setPrice(price);

			entityTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}

	}

	public static void deleteCar() {
		try {
			openConnection();
			entityTransaction.begin();
			System.out.println("Enter Car id You want to delete: ");
			Scanner scanner = new Scanner(System.in);
			int id = scanner.nextInt();

			
			Car car = entityManager.find(Car.class, id);
			entityManager.remove(car);

			System.out.println(id + " deleted!!");
			entityTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}

	}

	
	public static void getAllCarDetails() {
		try {
			List<Car> cars = new ArrayList<Car>();

			openConnection();
			entityTransaction.begin();
			Query query = entityManager.createQuery("Select car from Car car");

			cars = query.getResultList();

			for (Car car : cars) {
				System.out.println(car);
			}

			entityTransaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}

	}

}
