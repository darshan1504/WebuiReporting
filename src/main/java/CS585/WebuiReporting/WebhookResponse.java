package CS585.WebuiReporting;

public class WebhookResponse {
	public static class WebHookRequest {

		private String payload;
	}

	public static class Payload {

		private Repository repository;
		private Head_commit head_commit;

		public Object getHead_commit() {
			return head_commit;
		}

		public Object getRepository() {
			return repository;
		}
	}

	public static class Head_commit {

		private Committer committer;

		public Object getCommitter() {
			return committer;
		}
	}

	public static class Committer {
		private String name;
		private String email;

		public String getEmail() {
			return email;
		}

		public String getName() {
			return name;
		}

	}

	public static class Repository {

		private String url;

		public String getURL() {
			return url + ".git";
		}
	}

}
