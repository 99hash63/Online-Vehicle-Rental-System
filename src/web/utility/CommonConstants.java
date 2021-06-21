package web.utility;

//import java.lang.String;

public class CommonConstants {
	/** Constant for config.properties key for query file path */
	public static final String QUERY_XML = "queryFilePath";

	/** Constant for file path of config.properties */
	public static final String PROPERTY_FILE = "config.properties";

	/** Constant for query tag in BookingQuery.xml */
	public static final String TAG_NAME = "query";

	/** Constant for query id in BookingQuery.xml */
	public static final String ATTRIB_ID = "id";
	
	/** Constant for booking id prefix */
	public static final String BOOKING_ID_PREFIX = "b100";
	
	/** Constant for vehicle id prefix */
	public static final String VEHICLE_ID_PREFIX = "v100";
	
	/** Constant for driver id prefix */
	public static final String DRIVER_ID_PREFIX = "d100";
	
	/** Constant for customer id prefix */
	public static final String CUSTOMER_ID_PREFIX = "c100";
	
	/** Constant for administrator id prefix */
	public static final String ADMIN_ID_PREFIX = "a100";

	/** Constant for comma */
	public static final String COMMA = ",";

	/** Constant for url key of MySQL database in config.properties */
	public static final String URL = "url";

	/** Constant for user name key of MySQL database in config.properties */
	public static final String USERNAME = "username";

	/** Constant for password key of MySQL database in config.properties */
	public static final String PASSWORD = "password";

	/** Constant for driver name key of MySQL database in config.properties */
	public static final String DRIVER_NAME = "driverName";

	/** Constant for query id of drop_table in BookingQuery.xml */
	public static final String QUERY_ID_DROP_TABLE_BOOKING = "drop_table_booking";
	
	/** Constant for query id of drop_table in VehicleQuery.xml */
	public static final String QUERY_ID_DROP_TABLE_VEHICLE = "drop_table_vehicle";
	
	/** Constant for query id of drop_table in VehicleQuery.xml */
	public static final String QUERY_ID_DROP_TABLE_DRIVER = "drop_table_driver";
	
	/** Constant for query id of drop_table in VehicleQuery.xml */
	public static final String QUERY_ID_DROP_TABLE_CUSTOMER = "drop_table_customer";
	
	/** Constant for query id of drop_table in VehicleQuery.xml */
	public static final String QUERY_ID_DROP_TABLE_ADMIN = "drop_table_admin";

	/** Constant for query id of create_table in BookingQuery.xml */
	public static final String QUERY_ID_CREATE_TABLE_BOOKING = "create_booking_table";
	
	/** Constant for query id of create_table in BookingQuery.xml */
	public static final String QUERY_ID_CREATE_TABLE_VEHICLE = "create_vehicle_table";
	
	/** Constant for query id of create_table in BookingQuery.xml */
	public static final String QUERY_ID_CREATE_TABLE_DRIVER = "create_driver_table";
	
	/** Constant for query id of create_table in BookingQuery.xml */
	public static final String QUERY_ID_CREATE_TABLE_CUSTOMER = "create_customer_table";
	
	/** Constant for query id of create_table in BookingQuery.xml */
	public static final String QUERY_ID_CREATE_TABLE_ADMIN = "create_admin_table";

	/** Constant for query id of insert bookings in BookingQuery.xml */
	public static final String QUERY_ID_INSERT_BOOKINGS = "insert_booking";
	
	/** Constant for query id of insert bookings in BookingQuery.xml */
	public static final String QUERY_ID_INSERT_VEHICLES = "insert_vehicle";
	
	/** Constant for query id of insert bookings in BookingQuery.xml */
	public static final String QUERY_ID_INSERT_DRIVERS = "insert_driver";
	
	/** Constant for query id of insert bookings in BookingQuery.xml */
	public static final String QUERY_ID_INSERT_CUSTOMERS = "insert_customer";
	
	/** Constant for query id of insert bookings in BookingQuery.xml */
	public static final String QUERY_ID_INSERT_ADMINS = "insert_admin";

	/** Constant for query id of get an booking in BookingQuery.xml */
	public static final String QUERY_ID_GET_BOOKING = "booking_by_id";
	
	/** Constant for query id of get an booking in BookingQuery.xml */
	public static final String QUERY_ID_GET_VEHICLE = "vehicle_by_id";
	
	/** Constant for query id of get an booking in BookingQuery.xml */
	public static final String QUERY_ID_GET_DRIVER = "driver_by_id";
	
	/** Constant for query id of get an booking in BookingQuery.xml */
	public static final String QUERY_ID_GET_CUSTOMER = "customer_by_id";
	
	/** Constant for query id of get an booking in BookingQuery.xml */
	public static final String QUERY_ID_GET_ADMIN = "admin_by_id";

	/** Constant for query id of get all bookings in BookingQuery.xml */
	public static final String QUERY_ID_ALL_BOOKINGS = "all_bookings";
	
	/** Constant for query id of get all bookings in BookingQuery.xml */
	public static final String QUERY_ID_ALL_VEHICLES = "all_vehicles";
	
	/** Constant for query id of get all bookings in BookingQuery.xml */
	public static final String QUERY_ID_ALL_DRIVERS = "all_drivers";
	
	/** Constant for query id of get all bookings in BookingQuery.xml */
	public static final String QUERY_ID_ALL_CUSTOMERS = "all_customers";
	
	/** Constant for query id of get all bookings in BookingQuery.xml */
	public static final String QUERY_ID_ALL_ADMINS = "all_admins";

	/** Constant for query id of remove a booking in BookingQuery.xml */
	public static final String QUERY_ID_REMOVE_BOOKING = "remove_booking";
	
	/** Constant for query id of remove a booking in BookingQuery.xml */
	public static final String QUERY_ID_REMOVE_VEHICLE = "remove_vehicle";
	
	/** Constant for query id of remove a booking in BookingQuery.xml */
	public static final String QUERY_ID_REMOVE_DRIVER = "remove_driver";

	/** Constant for query id of remove a booking in BookingQuery.xml */
	public static final String QUERY_ID_REMOVE_CUSTOMER = "remove_customer";

	/** Constant for query id of remove a booking in BookingQuery.xml */
	public static final String QUERY_ID_REMOVE_ADMIN = "remove_ADMIN";
	
	/** Constant for query id of update a booking in BookingQuery.xml */
	public static final String QUERY_ID_UPDATE_BOOKING = "update_booking";
	
	/** Constant for query id of update a booking in BookingQuery.xml */
	public static final String QUERY_ID_UPDATE_VEHICLE = "update_vehicle";
	
	/** Constant for query id of update a booking in BookingQuery.xml */
	public static final String QUERY_ID_UPDATE_DRIVER = "update_driver";
	
	/** Constant for query id of update a booking in BookingQuery.xml */
	public static final String QUERY_ID_UPDATE_ADMIN = "update_admin";

	/** Constant for query id of get all booking ids in BookingQuery.xml */
	public static final String QUERY_ID_GET_BOOKING_IDS = "booking_ids";
	
	/** Constant for query id of get all booking ids in BookingQuery.xml */
	public static final String QUERY_ID_GET_VEHICLE_IDS = "vehicle_ids";
	
	/** Constant for query id of get all booking ids in BookingQuery.xml */
	public static final String QUERY_ID_GET_DRIVER_IDS = "driver_ids";
	
	/** Constant for query id of get all booking ids in BookingQuery.xml */
	public static final String QUERY_ID_GET_CUSTOMER_IDS = "customer_ids";
	
	/** Constant for query id of get all booking ids in BookingQuery.xml */
	public static final String QUERY_ID_GET_ADMIN_IDS = "admin_ids";
	
	/** Constant for query id of get an booking in BookingQuery.xml */
	public static final String QUERY_ID_GET_CUSTOMER_BOOKING = "booking_by_customer_id";
	
	/** Constant for Column index one */
	public static final int COLUMN_INDEX_ONE = 1;
	
	/** Constant for Column index two */
	public static final int COLUMN_INDEX_TWO = 2;
	
	/** Constant for Column index three */
	public static final int COLUMN_INDEX_THREE = 3;
	
	/** Constant for Column index four */
	public static final int COLUMN_INDEX_FOUR = 4;
	
	/** Constant for Column index five */
	public static final int COLUMN_INDEX_FIVE = 5;
	
	/** Constant for Column index six */
	public static final int COLUMN_INDEX_SIX = 6;
	
	/** Constant for Column index seven */
	public static final int COLUMN_INDEX_SEVEN = 7;
	
	/** Constant for Column index eight */
	public static final int COLUMN_INDEX_EIGHT = 8;
	
	/** Constant for Column index nine */
	public static final int COLUMN_INDEX_NINE = 9;
	
	/** Constant for Column index ten */
	public static final int COLUMN_INDEX_TEN = 10;
	
	/** Constant for Column index eleven */
	public static final int COLUMN_INDEX_ELEVEN = 11;
}
