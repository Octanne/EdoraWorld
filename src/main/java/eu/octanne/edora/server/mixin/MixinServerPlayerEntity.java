package eu.octanne.edora.server.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import eu.octanne.edora.server.EdoraServerPlayerEntity;
import eu.octanne.edora.server.economy.BankAccount;
import eu.octanne.edora.server.gourvern.Guilde;
import eu.octanne.edora.server.gourvern.Nation;
import eu.octanne.edora.server.gourvern.Town;

import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.network.ServerPlayerEntity;

@Mixin(ServerPlayerEntity.class)
public class MixinServerPlayerEntity implements EdoraServerPlayerEntity {

    @Unique
    protected Nation edora$nation;
    @Unique
    protected Town edora$town;
    @Unique
    protected Guilde edora$guilde;
    @Unique
    protected BankAccount edora$bankAccount;

    @Inject( method = "readCustomDataFromTag(Lnet/minecraft/nbt/CompoundTag;)V", at = @At("RETURN"))
    public void readCustomDataFromTag(CompoundTag tag, CallbackInfo info) {
        if(tag.contains("nationID")){
            edora$nation = Nation.getNationFromID(tag.getUuid("nationID"));
        }else{
            edora$nation = null;
        }
        if(tag.contains("townID")){
            edora$town = Town.getTownFromID(tag.getUuid("townID"));
        }else{
            edora$town = null;
        }
        if(tag.contains("guildeID")){
            edora$guilde = Guilde.getGuildeFromID(tag.getUuid("guildeID"));
        }else{
            edora$town = null;
        }
        // Bank data
        int oannes = 0;
        int nylus = 0; 
        if(tag.contains("amountOannes")){
            oannes = tag.getInt("amountOannes");
        }else{
            oannes = 0;
        }
        if(tag.contains("amountNylus")){
            nylus = tag.getInt("amountNylus");
        }else{
            nylus = 0;
        }
        edora$bankAccount = new BankAccount(oannes, nylus);
    }

	@Inject( method = "writeCustomDataToTag(Lnet/minecraft/nbt/CompoundTag;)V", at = @At("RETURN"))
    public void writeCustomDataToTag(CompoundTag tag, CallbackInfo info) {
        if(edora$nation != null) {
            tag.putUuid("nationID", edora$nation.getID());
        }
        if(edora$town != null) {
            tag.putUuid("townID", edora$town.getID());
        }
        if(edora$guilde != null) {
            tag.putUuid("guildeID", edora$guilde.getID());
        }
        tag.putInt("nylus", edora$bankAccount.getNylus());
        tag.putInt("oannes", edora$bankAccount.getOannes());
    }

	@Override
	public Nation getNation() {
		return edora$nation;
	}

	@Override
	public void changeNation(Nation nation) {
		edora$nation = nation;
	}

	@Override
	public Town getTown() {
		return edora$town;
	}

	@Override
	public void changeTown(Town town) {
		edora$town = town;
	}

	@Override
	public Guilde getGuilde() {
		return edora$guilde;
	}

	@Override
	public void changeGuilde(Guilde guilde) {
		edora$guilde = guilde;
	}

	@Override
	public BankAccount getBankAccount() {
		return edora$bankAccount;
	}
}
