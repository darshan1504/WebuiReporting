package CS585.WebuiReporting;


public class WebhookResponse {
	public static class WebHookRequest {

		private String payload;
	}



	/*
	 * Defines the attributes which are needed to be extracted from the payload Object
	 *
	 */

	public static class Payload {

		private Repository repository;
		private Head_commit head_commit;


		public Object getHead_commit(){
			return head_commit;
		}

		public Object getRepository() {
			return repository;
		}
	}


	/*
	 * Defines attribute needed from Head_Commit object
	 *
	*/
	public static class Head_commit {

		private Committer committer;

		public Object getCommitter(){
			return committer;
		}
	}


	/*
	 * Defines attributes needed from Committer object
	 * Gets committers email
	 *
	 */

	public static class Committer{
		private String name;
		private String email;

		public String getEmail(){
			return email;
		}

		public String getName(){
			return name;
		}

	}


	/*
	 * Defines attribute needed from Repository object
	 * Gets Repo URL and appends .git for cloning purposes
	 *
	 */

	public static class Repository {

		private String url;

		public String getURL(){
			return url + ".git";
		}
	}


}
