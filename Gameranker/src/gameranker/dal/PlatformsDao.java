package gameranker.dal;

public class PlatformsDao {
	protected ConnectionManager connectionManager;

	private static PlatformsDao instance = null;
	protected PlatformsDao() {
		connectionManager = new ConnectionManager();
	}
	public static PlatformsDao getInstance() {
		if(instance == null) {
			instance = new PlatformsDao();
		}
		return instance;
	}

}
