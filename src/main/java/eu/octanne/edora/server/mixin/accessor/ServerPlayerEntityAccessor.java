package eu.octanne.edora.server.mixin.accessor;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import eu.octanne.edora.server.economy.BankAccount;
import eu.octanne.edora.server.gourvern.Nation;
import eu.octanne.edora.server.gourvern.Town;
import net.minecraft.server.network.ServerPlayerEntity;

@Mixin(ServerPlayerEntity.class)
public interface ServerPlayerEntityAccessor {
    
    @Accessor("nation")
    Nation getNation();

    @Accessor("nation")
    public void changeNation(Nation nation);

    @Accessor("town")
    Town getTown();

    @Accessor("town")
    public void changeTown(Town town);

    @Accessor("guilde")
    Town getGuilde();

    @Accessor("guilde")
    public void changeGuilde(Town town);

    @Accessor("bankAccount")
    BankAccount getBankAccount();
}
