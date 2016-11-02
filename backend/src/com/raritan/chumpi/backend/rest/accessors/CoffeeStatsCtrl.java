package com.raritan.chumpi.backend.rest.accessors;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.raritan.chumpi.backend.data.CoffeeStatistic;
import com.raritan.chumpi.backend.data.Order;
import com.raritan.chumpi.backend.data.provider.OrderRepository;

@Path("/orders")
public class CoffeeStatsCtrl {

	@GET
	@Path("/stat")
	public CoffeeStatistic getAmountForUser(@QueryParam("userId")  int userId, @QueryParam("from") String from, @QueryParam("to") String to) {
		try {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("/byproduct")
	public Map<Integer, Integer> getOrdersByProduct(
			@QueryParam("from") Long from, // optional: start of time range (seconds since epoch)
			@QueryParam("to") Long to)     // optional: end of time range (seconds since epoch)
	{
		try {
			Date fromDate = from != null ? new Date(from * 1000) : null;
			Date toDate = to != null ? new Date(to * 1000) : null;

			Map<Integer, Integer> ordersByProduct = new HashMap<>();
			OrderRepository.INSTANCE.reloadCache();
			for (Order order : OrderRepository.INSTANCE.getAllInstances()) {
				if (fromDate != null && order.getDate().before(fromDate)) continue;
				if (toDate != null && order.getDate().after(toDate)) continue;

				int product = order.getRecipe().getSelector();
				Integer orders = ordersByProduct.get(product);
				ordersByProduct.put(product, orders == null ? 1 : orders + 1);
			}

			return ordersByProduct;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GET
	@Path("/byhour")
	public Map<Integer, Integer> getOrdersByHour() {
		try {
			Map<Integer, Integer> ordersByHour = new HashMap<>();
			OrderRepository.INSTANCE.reloadCache();
			for (Order order : OrderRepository.INSTANCE.getAllInstances()) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(order.getDate());
				int day = (cal.get(Calendar.DAY_OF_WEEK) + 5) % 7; // Sunday == 1, we want Monday == 0.
				int hour = cal.get(Calendar.HOUR_OF_DAY);
				int hourOfWeek = 24 * day + hour;
				Integer orders = ordersByHour.get(hourOfWeek);
				ordersByHour.put(hourOfWeek, orders == null ? 1 : orders + 1);
			}

			return ordersByHour;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
