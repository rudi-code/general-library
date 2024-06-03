package id.co.bjj.library.general.core.constants;

import java.util.HashMap;
import id.co.bjj.library.general.core.exceptions.Error;;


public class ErrorConstant {
	private ErrorConstant(){	
	}
	
	public static final Error SUCCESS 						= new Error("GEN-100", "Process successful", "Proses berhasil", 200);
	public static final Error FAILED  						= new Error("GEN-101", "Process failed", "Proses tidak berhasil", 400);	
	public static final Error PARSING_ERROR					= new Error("GEN-102", "Parsing error", "Kesalahan parsing", 400);		
	public static final Error INVALID_PARAMETER				= new Error("GEN-103", "Invalid parameter", "Parameter tidak sesuai", 400);
	public static final Error INVALID_DATA_ENTRY 			= new Error("GEN-104", "Invalid data entry", "Data yang dimasukkan tidak sesuai", 400);
	public static final Error UNAUTHORIZED_ACCESS			= new Error("GEN-105", "Unauthorized access", "Akses tidak sah", 401);	    
	public static final Error UNAUTHORIZED_ACCESS_EXPIRED   = new Error("GEN-106", "Unauthorized access", "Akses tidak sah", 401);
	public static final Error DATA_NOT_FOUND				= new Error("GEN-107", "Data not found. ", "Data tidak ditemukan", 404);
	public static final Error NULL_ENTITY					= new Error("GEN-108", "Entity can't be null or empty", "Entitas tidak bisa kosong.",400);
	public static final Error THREAD_TIMEOUT				= new Error("GEN-109", "Thread process timeout", "Waktu habis untuk thread",500);
	public static final Error UNAUTHORIZED_ACCESS_DEVICE	= new Error("GEN-110", "Unauthorized access. ", "Akses tidak sah.",401);
	public static final Error UNAUTHORIZED_ACCESS_BLACKLIST	= new Error("GEN-111", "Unauthorized access. ", "Akses tidak sah.",401);
	public static final Error UNKNOWN_ERROR					= new Error("GEN-112", "Unknown error has occured.", "Terjadi error yang tidak diketahui.",500);
	public static final Error INVALID_EXPIRED_DATE			= new Error("GEN-113", "Expired Date can't before current date. ", "Tanggal Kadaluarsa tidak bisa kurang dari tanggal sekarang. ",400);
	public static final Error BAD_REQUEST					= new Error("GEN-114", "Bad Request", "Request buruk", 400);
	public static final Error API_NOT_FOUND					= new Error("GEN-115", "API is not found", "API tidak ditemukan", 404);	
	public static final Error INTERNAL_SERVER_ERROR			= new Error("GEN-116", "Unexpected internal server error", "Error yang tidak terduga pada dalam internal server", 500);
	
	public static final Error ENCRYPTION_ERROR 				= new Error("ENR-001", "Encryption error", "Enkripsi gagal", 400);
	public static final Error DECRYPTION_ERROR 				= new Error("ENR-002", "Decryption error", "Dekripsi gagal", 400);
	
	public static final Error QUERY_DATA_TYPE_ERROR 		= new Error("QRY-001", "Invalid data type for query condition", "Tipe data tidak valid untuk kondisi query", 400);
	public static final Error QUERY_FIELD_NOT_FOUND 		= new Error("QRY-002", "Field name is not found in model", "Nama field tidak ditemukan pada model", 400);
	public static final Error QUERY_SEPARATOR_INVALID		= new Error("QRY-003", "Query separator only and & or", "Pemisah query hanya boleh and & or", 400);
	public static final Error QUERY_DATA_TYPE_CONVERT_ERROR = new Error("QRY-004", "Convert data type error", "Konversi tipe data gagal", 400);
	public static final Error QUERY_VALUE_MUST_LIST			= new Error("QRY-005", "Query value must be list", "Nilai query harus list", 400);
	public static final Error QUERY_COMPARE_FIELD_INVALID   = new Error("QRY-006", "Compare data type invalid", "Permbandingan tipe data tidak valid", 400);
	public static final Error QUERY_SECOND_VALUE_EMPTY		= new Error("QRY-007", "Query second value is not allowed null/empty", "Query nilai kedua tidak boleh kosong", 400);
	
	public static final Error REQUIRED_FIELD 			= new Error("VAL-100","Field is Required","Field harus diisi", 400);
	public static final Error INVALID_CONTACT_NUMBER 	= new Error("VAL-101","Invalid Contact number value, minimum value > 8 <=15 digit","Nomor kontak tidak valid, nilai minimum > 8 <=13 digit", 400);
	public static final Error INVALID_NUMBER_FORMAT 	= new Error("VAL-102","Invalid number format","Format nomor tidak valid", 400);
	public static final Error INVALID_DATE_FORMAT 		= new Error("VAL-103","Invalid date format","Format tanggal tidak valid", 400);
	public static final Error INVALID_EMAIL_VALUE 		= new Error("VAL-104","Invalid email format","format email tidak valid", 400);	
	public static final Error INVALID_DATE_RANGE 		= new Error("VAL-105","Invalid date range between StartDate and EndDate","Jarak antara tanggal mulai dan tanggal akhir tidak valid", 400);
	public static final Error INVALID_MIN_MAX 			= new Error("VAL-106","Invalid value between MIN and MAX","Jarak antara minimum dan maksimum tidak valid", 400);
	public static final Error INVALID_POSITIVE_NUMBER	= new Error("VAL-107","Number must be positive","Angka harus positif", 400);
    public static final Error WITHOUT_SPECIAL_CHARACTER = new Error("VAL-108","Special character is forbidden","Tidak boleh menggunakan karakter spesial", 400);
    public static final Error INVALID_BOOLEAN_FORMAT	= new Error("VAL-109","Invalid Boolean Format, only true or false is accepted","Format Boolean tidak valid, hanya true atau false yang diterima", 400);
    public static final Error DATE_CANNOT_BACKDATE 		= new Error("VAL-110","Date cannot backdate","Tanggal tidak dapat mundur", 400);
    public static final Error DATE_MUST_BE_EARLIER 		= new Error("VAL-111","First date must be earlier than second date","Tanggal pertama harus lebih awal dari tanggal kedua", 400);
	 
    public static final Error NOT_ALLOWED_EXTENSION 	= new Error("FIL-001", "File extension is not allowed", "Extension berkas tidak diperbolehkan", 400);
    public static final Error FAILED_CREATE_TIFF		= new Error("FIL-002", "Failed to create tiff file", "Gagal membuat berkas tiff", 400);
	public static final Error FAILED_CREATE_PDF			= new Error("FIL-003", "Failed to create pdf file",  "Gagal membuat berkas pdf", 400);
	public static final Error FAILED_CREATE_FILE		= new Error("FIL-004", "Failed to create file", "Gagal dalam membuat berkas", 400);
	public static final Error FAILED_DELETE_FILE		= new Error("FIL-005", "Failed to delete file", "Gagal dalam menghapus berkas", 400);
	public static final Error FILE_NAME_EMPTY 			= new Error("FIL-006", "Filename is not allowed null or empty", "Nama file tidak diperbolehkan kosong", 400);
	public static final Error FAILED_COMPRESS_FILE		= new Error("FIL-007", "Failed to compress file", "Gagal dalam proses kompres berkas", 400);
	public static final Error FAILED_CREATE_ZIP			= new Error("FIL-008", "Failed to create zip file", "Gagal membuat berkas zip", 400);
	public static final Error FAILED_GET_FILE_SIZE		= new Error("FIL-009", "Failed to get file size", "Gagal dalam mendapatkan ukuran berkas", 400);
	public static final Error CHECKSUM_FAILED			= new Error("FIL-010", "Failed to generate checksum file", "Gagal dalam membuat berkas checksum", 400);
	public static final Error FAILED_GET_CONTENT_TYPE	= new Error("FIL-011", "Failed to get content type file", "Gagal dalam mendapatkan tipe konten berkas", 400);
	
	public static final Error KAFKA_ERROR_INTERRUPTED	= new Error("KFK-001", "Kafka process execution failed because interrupted", "Process eksekusi kafka gagal dikarenakan terganggu/terputus", 400);
	public static final Error KAFKA_ERROR_UNKNOWN		= new Error("KFK-002", "Unknown error for kafka process", "Error yang tidak diketahui oleh proces kafka", 400);
		
	public static final String INVALID_EMAIL_VALUE_KEY ="INVALID_EMAIL_VALUE";
	public static final String INVALID_NUMBER_FORMAT_KEY="INVALID_NUMBER_FORMAT";
	public static final String INVALID_CONTACT_NUMBER_KEY = "INVALID_CONTACT_NUMBER";
	public static final String INVALID_DATE_FORMAT_KEY = "INVALID_DATE_FORMAT";
	public static final String REQUIRED_FIELD_KEY = "REQUIRED_FIELD";
	public static final String INVALID_DATE_RANGE_KEY = "INVALID_DATE_RANGE";
	public static final String INVALID_MIN_MAX_KEY = "INVALID_MIN_MAX";
	public static final String INVALID_POSITIVE_NUMBER_KEY = "INVALID_POSITIVE_NUMBER";
    public static final String WITHOUT_SPECIAL_CHARACTER_KEY = "WITHOUT_SPECIAL_CHARACTER";
	public static final String UNKNOWN_ERROR_KEY = "UNKNOWN_ERROR";
	public static final String INVALID_EXPIRED_DATE_KEY = "INVALID_EXPIRED_DATE";
	public static final String INVALID_BOOLEAN_FORMAT_KEY = "INVALID_BOOLEAN_FORMAT";
	public static final String DATE_CANNOT_BACKDATE_KEY = "DATE_CANNOT_BACKDATE";
	public static final String DATE_MUST_BE_EARLIER_KEY = "DATE_MUST_BE_EARLIER";
	public static final String QUERY_SECOND_VALUE_EMPTY_KEY = "QUERY_SECOND_VALUE_EMPTY";
	
	protected static HashMap<String, Error> errors ;
	static {
		errors = new HashMap<>();
		errors.put(INVALID_EMAIL_VALUE_KEY, INVALID_EMAIL_VALUE);
		errors.put(INVALID_NUMBER_FORMAT_KEY, INVALID_NUMBER_FORMAT);
		errors.put(INVALID_CONTACT_NUMBER_KEY, INVALID_CONTACT_NUMBER);
		errors.put(INVALID_DATE_FORMAT_KEY, INVALID_DATE_FORMAT);
		errors.put(REQUIRED_FIELD_KEY, REQUIRED_FIELD);
		errors.put(INVALID_DATE_RANGE_KEY, INVALID_DATE_RANGE);
		errors.put(UNKNOWN_ERROR_KEY, UNKNOWN_ERROR);
		errors.put(INVALID_MIN_MAX_KEY, INVALID_MIN_MAX);
		errors.put(INVALID_POSITIVE_NUMBER_KEY, INVALID_POSITIVE_NUMBER);
        errors.put(WITHOUT_SPECIAL_CHARACTER_KEY, WITHOUT_SPECIAL_CHARACTER);
        errors.put(INVALID_EXPIRED_DATE_KEY, INVALID_EXPIRED_DATE);
        errors.put(INVALID_BOOLEAN_FORMAT_KEY, INVALID_BOOLEAN_FORMAT);
        errors.put(DATE_CANNOT_BACKDATE_KEY, DATE_CANNOT_BACKDATE);
        errors.put(DATE_MUST_BE_EARLIER_KEY, DATE_MUST_BE_EARLIER);
        errors.put(QUERY_SECOND_VALUE_EMPTY_KEY, QUERY_SECOND_VALUE_EMPTY);
        
	}

	public static Error getError (String exceptionKey){
		return errors.get(exceptionKey);
	}
}
