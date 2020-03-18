package gameranker.dal;

public class PublishersDao {
	protected ConnectionManager connectionManager;

	private static PublishersDao instance = null;
	protected PublishersDao() {
		connectionManager = new ConnectionManager();
	}
	public static PublishersDao getInstance() {
		if(instance == null) {
			instance = new PublishersDao();
		}
		return instance;
	}

}
