package com.st.rbac.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.st.rbac.pojo.Pic;
import com.st.rbac.pojo.Product;
import com.st.rbac.pojo.ResponseResult;
import com.st.rbac.pojo.SocketMessage;
import com.st.rbac.service.IPicService;
import com.st.rbac.service.IProductService;
import com.st.rbac.service.impl.ProductServiceImpl;
import com.st.rbac.websocket.MessageSocket;

@Controller
public class ProductController {

	@Autowired
	private IProductService iProductService;

	@Autowired
	private IPicService iPicService;

	@Autowired
	private MessageSocket msgScoket;

	@RequestMapping("/product/productview.action")
	public ModelAndView productView(Integer pid) {

		// 根据PiD 获取所有图片
		Product product = iProductService.getById(pid);
		
		ModelAndView mView = new ModelAndView();
		mView.addObject("product", product);
		mView.addObject("error", "错误");
		mView.setViewName("product");
		return mView;
	}
	@RequestMapping("/product/selectview.action")
	public String selectView() {

		return "productList";
	}

	@RequestMapping("/product/updateview.action")
	public String updateView() {

		return "productupdate";
	}

	@RequestMapping("/product/select.action")
	@ResponseBody
	public ResponseResult select() {
		List<Product> list = iProductService.select();

		ResponseResult result = new ResponseResult();
		result.setCode(0);
		result.setCount(list.size());
		result.setData(list);
		result.setMsg("XXX");

		return result;
	}

	@RequestMapping("/role/product/addview.action")
	public String addView() {
		return "productadd";
	}
	@RequestMapping("/product/add.action")
	@ResponseBody
	public ResponseResult add(Product product, String[] pics) throws IOException {

		ResponseResult result = new ResponseResult();
		
		// 将产品信息插入数据库
		int res = iProductService.add(product);
		
		if (res <= 0) {
			
			result.setCode(-1);
			result.setMsg("产品插入失败");
			
		} else {
		
			// 将图片信息插入数据库
			int res2 = iPicService.addBatch(pics, product.getPid());
		
			if (res <= 0) {
				result.setCode(-2);
				result.setMsg("图片插入失败");
			} else {
				result.setCode(0);
			}
			
			// 当添加成功后, 通过WebSocket向所有的用户发送一条消息
			SocketMessage msg = new SocketMessage();
			msg.setMsg("刚添加了一条产品[" + product.getPname() + "]");
			msgScoket.sendMsg(msg);
		}
		return result;
	}

	/*
	 * 上传文件, 1. 需要依赖commons-fileuplad.jar, commons-io.jar 2.
	 * 需要在SpringMVC.xml文件中添加文件解析器的设置 3.
	 */
	@RequestMapping("/product/uploadPic.action")
	@ResponseBody
	public ResponseResult uploadPic(MultipartFile file, HttpServletRequest request) {
		// 文件保存到哪里?
		String savePath = request.getSession().getServletContext().getRealPath("/upload");

		// 保存文件名
		String fileName = file.getOriginalFilename();
		String subfix = fileName.substring(fileName.lastIndexOf("."));
		String saveFile = UUID.randomUUID() + subfix;
		
		// 文件如何保存?
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			inputStream = file.getInputStream();

			outputStream = new FileOutputStream(savePath + "/" + saveFile);

			IOUtils.copy(inputStream, outputStream);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Pic picRes = new Pic();
		picRes.setPicurl("upload/"+saveFile);
		
		ResponseResult result = new ResponseResult();
		result.setCode(0);
		result.setMsg("上传成功");
		result.setData(picRes);

		return result;
	}
	
	@RequestMapping("/product/getSalesCount.action")
	@ResponseBody
	public List<Map<String, Object>> getSalesCount(){
		
		// 多个产品, 以及他们的销售数量
		// [{'pname': '宝马X5', 'count': 10},{'pname': '宝马730', 'count': 20}]
		
		List<Map<String, Object>> list = new ArrayList<>();
		
		Map<String, Object> map1 = new HashMap<>();
		map1.put("pname", "宝马X5");
		map1.put("count", 10);
		Map<String, Object> map2 = new HashMap<>();
		map2.put("pname", "宝马X3");
		map2.put("count", 300);
		Map<String, Object> map3 = new HashMap<>();
		map3.put("pname", "宝马730");
		map3.put("count", 50);
		Map<String, Object> map4 = new HashMap<>();
		map4.put("pname", "宝马750");
		map4.put("count", 1000);
		
		list.add(map1);
		list.add(map2);
		list.add(map3);
		list.add(map4);
		
		return list;
	}
}
