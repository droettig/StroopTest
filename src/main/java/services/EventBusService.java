package services;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import events.StroopEvent;

@Singleton
public class EventBusService
{
	private EventBus eventBus;
	
	@Inject
	public EventBusService()
	{
		this.eventBus = new EventBus();
	}

	public void register(Object subscriber)
	{
		eventBus.register(subscriber);
	}
	
	public void post(StroopEvent event)
	{
		eventBus.post(event);
	}
}
