package eu.octanne.edora.server.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import eu.octanne.edora.server.economy.BankAccount;
import eu.octanne.edora.server.gourvern.Guilde;
import eu.octanne.edora.server.gourvern.Nation;
import eu.octanne.edora.server.gourvern.Town;

import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.network.ServerPlayerEntity;

@Mixin(ServerPlayerEntity.class)
public class MixinServerPlayerEntity {

    private Nation nation;
    private Town town;
    private Guilde guilde;
    private BankAccount bankAccount;

    @Inject( method = "readCustomDataFromTag(Lnet/minecraft/nbt/CompoundTag)V", at = @At("RETURN"))
    public void readCustomDataFromTag(CompoundTag tag, CallbackInfo info) {
        if(tag.contains("nationID")){
            nation = Nation.getNationFromID(tag.getUuid("nationID"));
        }else{
            nation = null;
        }
        if(tag.contains("townID")){
            town = Town.getTownFromID(tag.getUuid("townID"));
        }else{
            town = null;
        }
        if(tag.contains("guildeID")){
            guilde = Guilde.getGuildeFromID(tag.getUuid("guildeID"));
        }else{
            town = null;
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
        bankAccount = new BankAccount(oannes, nylus);
    }

	@Inject( method = "writeCustomDataFromTag(Lnet/minecraft/nbt/CompoundTag)V", at = @At("RETURN"))
    public void writeCustomDataFromTag(CompoundTag tag, CallbackInfo info) {
        if(nation != null) {
            tag.putUuid("nationID", nation.getID());
        }
        if(town != null) {
            tag.putUuid("townID", town.getID());
        }
        if(guilde != null) {
            tag.putUuid("guildeID", guilde.getID());
        }
        tag.putInt("nylus", bankAccount.getNylus());
        tag.putInt("oannes", bankAccount.getOannes());
    }
}
