package gameranker.dal;

public class GenresDao {
	protected ConnectionManager connectionManager;

	private static GenresDao instance = null;
	protected GenresDao() {
		connectionManager = new ConnectionManager();
	}
	public static GenresDao getInstance() {
		if(instance == null) {
			instance = new GenresDao();
		}
		return instance;
	}

}
