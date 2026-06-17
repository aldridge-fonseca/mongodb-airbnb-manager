package com.mongodb.airbnb;

import com.mongodb.client.*;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AirbnbListingManager {

    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static MongoCollection<Document> collection;

    private static void connectToMongoDB() {
        String connectionString = "mongodb://localhost:27017";
        try {
            mongoClient = MongoClients.create(connectionString);
            database = mongoClient.getDatabase("sample_airbnb");
            collection = database.getCollection("listingsAndReviews");
            System.out.println("Successfully connected to MongoDB.");
        } catch (Exception e) {
            System.err.println("Error connecting to MongoDB: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void closeMongoDBConnection() {
        if (mongoClient != null) {
            try {
                mongoClient.close();
                System.out.println("\nMongoDB connection closed.");
            } catch (Exception e) {
                System.err.println("Error closing MongoDB connection: " + e.getMessage());
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n+-------------------------------------------+");
        System.out.println("|           Airbnb Listing Manager          |");
        System.out.println("+-------------------------------------------+");
        System.out.println("| 1. Create Listing                         |");
        System.out.println("| 2. Read Listings by Neighbourhood         |");
        System.out.println("| 3. Update Listing Host Name               |");
        System.out.println("| 4. Delete Listing by ID                   |");
        System.out.println("| 5. Exit                                   |");
        System.out.println("+-------------------------------------------+");
        System.out.print("Choose an operation (1-5): ");
    }

    public static void main(String[] args) {
        connectToMongoDB();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 5) {
            displayMenu();
            try {
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        createListing(scanner);
                        break;
                    case 2:
                        readListingByNeighbourhood(scanner);
                        break;
                    case 3:
                        updateListing(scanner);
                        break;
                    case 4:
                        deleteListing(scanner);
                        break;
                    case 5:
                        System.out.println("Exiting application...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                choice = 0;
            } catch (Exception e) {
                System.err.println("An unexpected error occurred: " + e.getMessage());
                choice = 0;
            }
            if (choice != 5) {
                System.out.print("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }

        scanner.close();
        closeMongoDBConnection();
    }

    private static void createListing(Scanner scanner) {
        System.out.println("\n--- Create New Listing ---");
        try {
            System.out.print("ID (String): ");
            String id = scanner.nextLine();
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Host ID (long): ");
            long hostId = scanner.nextLong();
            scanner.nextLine();
            System.out.print("Host Name: ");
            String hostName = scanner.nextLine();
            System.out.print("Neighbourhood Group: ");
            String neighbourhoodGroup = scanner.nextLine();
            System.out.print("Neighbourhood: ");
            String neighbourhood = scanner.nextLine();
            System.out.print("Latitude (double): ");
            double latitude = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Longitude (double): ");
            double longitude = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Room Type: ");
            String roomType = scanner.nextLine();
            System.out.print("Price (double): ");
            double price = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Minimum Nights (int): ");
            int minimumNights = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Number of Reviews (int): ");
            int numberOfReviews = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Last Review (YYYY-MM-DD or empty): ");
            String lastReview = scanner.nextLine();
            System.out.print("Reviews Per Month (double or empty): ");
            String reviewsPerMonthStr = scanner.nextLine();
            Double reviewsPerMonth = reviewsPerMonthStr.isEmpty() ? null : Double.parseDouble(reviewsPerMonthStr);
            System.out.print("Calculated Host Listings Count (int): ");
            int calculatedHostListingsCount = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Availability 365 (int): ");
            int availability365 = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Number of Reviews LTM (int): ");
            int numberOfReviewsLtm = scanner.nextInt();
            scanner.nextLine();
            System.out.print("License: ");
            String license = scanner.nextLine();

            Document doc = new Document("id", id)
                    .append("name", name)
                    .append("host_id", hostId)
                    .append("host_name", hostName)
                    .append("neighbourhood_group", neighbourhoodGroup)
                    .append("neighbourhood", neighbourhood)
                    .append("latitude", latitude)
                    .append("longitude", longitude)
                    .append("room_type", roomType)
                    .append("price", price)
                    .append("minimum_nights", minimumNights)
                    .append("number_of_reviews", numberOfReviews)
                    .append("last_review", lastReview)
                    .append("reviews_per_month", reviewsPerMonth)
                    .append("calculated_host_listings_count", calculatedHostListingsCount)
                    .append("availability_365", availability365)
                    .append("number_of_reviews_ltm", numberOfReviewsLtm)
                    .append("license", license);

            collection.insertOne(doc);
            System.out.println("Listing created successfully!");

        } catch (InputMismatchException e) {
            System.err.println("Invalid input format. Please enter the correct data type.");
            scanner.nextLine();
        } catch (Exception e) {
            System.err.println("An error occurred during listing creation: " + e.getMessage());
        }
    }

    private static void readListingByNeighbourhood(Scanner scanner) {
        System.out.print("\nEnter neighbourhood to fetch listings: ");
        String neighbourhood = scanner.nextLine();
        Document query = new Document("neighbourhood", neighbourhood);

        System.out.println("\n--- Searching Listings in: " + neighbourhood + " ---");
        FindIterable<Document> listings = collection.find(query);

        try (MongoCursor<Document> cursor = listings.iterator()) {
            if (!cursor.hasNext()) {
                System.out.println("No listings found.");
                return;
            }
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                System.out.println("--------------------");
                System.out.println("  ID: " + doc.get("id"));
                System.out.println("  Name: " + doc.getString("name"));
                System.out.println("  Host Name: " + doc.getString("host_name"));
                System.out.println("  Neighbourhood: " + doc.getString("neighbourhood"));
                Object priceObj = doc.get("price");
                System.out.println("  Price: " + (priceObj != null ? priceObj.toString() : "N/A"));
            }
            System.out.println("--------------------");
        } catch (Exception e) {
            System.err.println("An error occurred while reading listings: " + e.getMessage());
        }
    }

    private static void updateListing(Scanner scanner) {
        System.out.println("\n--- Update Listing Host Name ---");
        System.out.print("Enter the ID (String) of the listing to update: ");
        String listingIdToUpdate = scanner.nextLine();
        System.out.print("Enter the new Host Name: ");
        String newHostName = scanner.nextLine();

        Document query = new Document("id", listingIdToUpdate);
        Document update = new Document("$set", new Document("host_name", newHostName));

        try {
            UpdateResult result = collection.updateOne(query, update);

            if (result.getMatchedCount() == 0) {
                System.out.println("No listing found with the specified ID: " + listingIdToUpdate);
            } else if (result.getModifiedCount() == 0) {
                System.out.println("Listing found, but no changes were made (host name might be the same).");
            } else {
                System.out.println("Listing updated successfully! (" + result.getModifiedCount() + " document modified)");
                printListingDetailsById(listingIdToUpdate);
            }
        } catch (Exception e) {
            System.err.println("An error occurred during the update operation: " + e.getMessage());
        }
    }

    private static void printListingDetailsById(String listingId) {
        Document query = new Document("id", listingId);
        Document listing = null;

        try {
            listing = collection.find(query).first();
            if (listing != null) {
                System.out.println("\n--- Details for Listing ID: " + listingId + " ---");
                System.out.println("  ID: " + listing.get("id"));
                System.out.println("  Name: " + listing.getString("name"));
                System.out.println("  Host Name: " + listing.getString("host_name"));
                System.out.println("  Neighbourhood: " + listing.getString("neighbourhood"));
                Object priceObj = listing.get("price");
                System.out.println("  Price: " + (priceObj != null ? priceObj.toString() : "N/A"));
                System.out.println("  Number of Reviews: " + listing.getInteger("number_of_reviews", 0));
                System.out.println("-----------------------------");
            } else {
                System.out.println("No listing found with the ID: " + listingId);
            }
        } catch (Exception e) {
            System.err.println("An error occurred while fetching listing details: " + e.getMessage());
        }
    }

    private static void deleteListing(Scanner scanner) {
        System.out.println("\n--- Delete Listing by ID ---");
        System.out.print("Enter the ID (String) of the listing to delete: ");
        String listingIdToDelete = scanner.nextLine();
        Document query = new Document("id", listingIdToDelete);

        try {
            System.out.print("Are you sure you want to delete listing '" + listingIdToDelete + "'? (yes/no): ");
            String confirmation = scanner.nextLine();

            if ("yes".equalsIgnoreCase(confirmation)) {
                DeleteResult result = collection.deleteOne(query);
                if (result.getDeletedCount() > 0) {
                    System.out.println("Listing deleted successfully!");
                } else {
                    System.out.println("No listing found with the ID: " + listingIdToDelete + ". Nothing deleted.");
                }
            } else {
                System.out.println("Deletion cancelled.");
            }
        } catch (Exception e) {
            System.err.println("An error occurred during the delete operation: " + e.getMessage());
        }
    }
}
