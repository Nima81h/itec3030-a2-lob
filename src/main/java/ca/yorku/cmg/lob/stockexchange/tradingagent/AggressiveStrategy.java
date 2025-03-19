package ca.yorku.cmg.lob.stockexchange.tradingagent;

import ca.yorku.cmg.lob.orderbook.Ask;
import ca.yorku.cmg.lob.orderbook.Bid;
import ca.yorku.cmg.lob.stockexchange.StockExchange;
import ca.yorku.cmg.lob.stockexchange.events.BadNews;
import ca.yorku.cmg.lob.stockexchange.events.Event;
import ca.yorku.cmg.lob.stockexchange.events.GoodNews;
import ca.yorku.cmg.lob.stockexchange.events.NewsBoard;
import ca.yorku.cmg.lob.trader.Trader;
import ca.yorku.cmg.lob.tradestandards.IOrder;

public class AggressiveStrategy implements ITradingStrategy {

	private  Trader t;
	private  StockExchange e;
	private  NewsBoard n;

	public AggressiveStrategy(Trader t, StockExchange exc, NewsBoard n) {
		this.t = t;
		this.e = e;
		this.n = n;
	}

	@Override
	public void actOnEvent(Event e, int pos, int price) {
		super(t,e,n);
		IOrder newOrder = null;

		if (e instanceof GoodNews) {
			newOrder = new Bid(t, e.getSecurity(),
					(int) Math.round(price * 1.05),
					(int) Math.round(pos * 0.5),
					e.getTime());
		} else if (e instanceof BadNews) {
			newOrder = new Ask(t, e.getSecurity(),
					(int) Math.round(price * 0.90),
					(int) Math.round(pos * 0.8),
					e.getTime());
		} else {
			System.out.println("Unknown event type");
		}

		if (newOrder != null) {
			exc.submitOrder(newOrder, e.getTime());
		}
	}
}

