package com.kdt.endpoint;

/*@ServerEndpoint(value="/chat",configurator = HttpSessionConfigurator.class)
public class ChatEndpoint {
	private MessagesService service = ContextProvider.getSpring().getBean(MessagesService.class);
	
	private static Gson gson = new GsonBuilder().registerTypeAdapter(Timestamp.class, new JsonSerializer<Timestamp>() {
		private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd. HH:mm");

		@Override
		public JsonElement serialize(Timestamp src, Type typeOfSrc, JsonSerializationContext context) {
			return new JsonPrimitive(sdf.format(src));
		}

	}).create();
	//여러 사용자 session에 저장됨 ,중복허용 x
	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<>()) ;
	private static EvictingQueue<MessagesDTO> chatlist = EvictingQueue.create(30);
	List<MessagesDTO> MessageList = service.selectAll();
	//private SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
	private HttpSession hsession;
	

	@OnOpen//실행되는 순간
	public void onConnect(Session client, EndpointConfig config) throws Exception{//자료형 Session
		System.out.println("접속 확인");
		clients.add(client);
		
		this.hsession = (HttpSession)config.getUserProperties().get("hsession");
		MessageList.get(0).getMessage();
		client.getBasicRemote().sendText(gson.toJson(MessageList));
	}
	//데이터 받을 때
	@OnMessage
	public void onMessage(String message) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd. HH:mm");
		MessagesDTO dto = new MessagesDTO();
		String id = (String)hsession.getAttribute("id");
		dto.setSeq(0);
		dto.setWriter(id);
		dto.setMessage(message);
		dto.setWrite_date(sdf.format(System.currentTimeMillis()));
		service.insert(dto);
		chatlist.add(dto);
		synchronized (clients) {
			for(Session client : clients) {
				try {
					client.getBasicRemote().sendText(gson.toJson(dto));//메세지 발송
				} catch (Exception e) {
					// 사용자가 나가서 메세지 못 보낼 때 접속을 끊은 사람에게 못감
					e.printStackTrace();
				}
			}
		}
	}
	//접속 끊어짐
	@OnClose
	public void onClose(Session client) {
		clients.remove(client);
		System.out.println("cient disconnected..");
	}

	@OnError
	public void onError(Session client, Throwable t) {
		clients.remove(client);
		t.printStackTrace();
	}
}*/
