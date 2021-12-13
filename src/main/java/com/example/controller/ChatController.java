package com.example.controller;

import com.example.po.User;
import com.example.service.LoginService;
import com.example.service.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.websocket.Session;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class ChatController {

	@Autowired
	LoginService loginservice;
	

	@RequestMapping("/onlineusers")
	@ResponseBody
	public Set<String> onlineusers(@RequestParam("currentuser") String currentuser) {
		ConcurrentHashMap<String, Session> map = WebSocketServer.getSessionPools();
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		Set<String> nameset = new HashSet<String>();
		while (it.hasNext()) {
			String entry = it.next();
			if (!entry.equals(currentuser))
				nameset.add(entry);
		}
		return nameset;
	}


	@RequestMapping("getuid")
	@ResponseBody
	public User getuid(@RequestParam("username") String username) {
		Long a = loginservice.getUidbyname(username);
		User u = new User();
		u.setUid(a);
		return u;
	}
}
