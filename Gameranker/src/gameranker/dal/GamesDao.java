package gameranker.dal;

public class GamesDao {
	protected ConnectionManager connectionManager;

	private static GamesDao instance = null;
	protected GamesDao() {
		connectionManager = new ConnectionManager();
	}
	public static GamesDao getInstance() {
		if(instance == null) {
			instance = new GamesDao();
		}
		return instance;
	}

}
