# Dataset

This project uses the **San Francisco Airbnb Listings** dataset sourced from [Inside Airbnb](http://insideairbnb.com/get-the-data/), a public, community-sourced platform that compiles Airbnb listing data from cities worldwide.

## File

| File | Description | Size |
|------|-------------|------|
| `listings.csv` | Detailed listing data for San Francisco (~7,800+ records) | ~1.3 MB |

## Schema

Each row represents one Airbnb listing with the following columns:

| Column | Type | Description |
|--------|------|-------------|
| `id` | string | Unique listing identifier |
| `name` | string | Listing title |
| `host_id` | long | Host's unique ID |
| `host_name` | string | Host's display name |
| `neighbourhood_group` | string | Borough or district group |
| `neighbourhood` | string | Specific neighbourhood |
| `latitude` | double | Geographic latitude |
| `longitude` | double | Geographic longitude |
| `room_type` | string | Entire home/apt, Private room, or Shared room |
| `price` | double | Nightly price in USD |
| `minimum_nights` | int | Minimum stay requirement |
| `number_of_reviews` | int | Total reviews received |
| `last_review` | string | Date of most recent review |
| `reviews_per_month` | double | Average reviews per month |
| `calculated_host_listings_count` | int | Listings owned by this host |
| `availability_365` | int | Days available in next 365 days |
| `number_of_reviews_ltm` | int | Reviews in last 12 months |
| `license` | string | City registration license |

## Loading into MongoDB

Use `mongoimport` to load the CSV into your MongoDB instance:

```bash
mongoimport --db sample_airbnb --collection listingsAndReviews \
  --type csv --headerline --file data/listings.csv
```

Or use MongoDB Compass for a GUI-based import.

## License & Attribution

Data sourced from Inside Airbnb under their public-use terms. This dataset is provided for educational and non-commercial use.
