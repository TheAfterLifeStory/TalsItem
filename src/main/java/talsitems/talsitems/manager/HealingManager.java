package talsitems.talsitems.manager;

import org.bukkit.event.entity.EntityRegainHealthEvent;

public class HealingManager {

    public void setHealing(EntityRegainHealthEvent e,int heal)
    {
        if(heal == 1)
        {
            return;
        }

        if(e.isCancelled())
        {
            return;
        }

        e.setAmount(heal);
    }
}
