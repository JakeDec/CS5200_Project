package gameranker.dal;

public class UserHasGameDao {
	protected ConnectionManager connectionManager;

	private static UserHasGameDao instance = null;
	protected UserHasGameDao() {
		connectionManager = new ConnectionManager();
	}
	public static UserHasGameDao getInstance() {
		if(instance == null) {
			instance = new UserHasGameDao();
		}
		return instance;
	}

}
