package eu.octanne.edora.server.mixin;

import com.mojang.authlib.GameProfile;

import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import eu.octanne.edora.EdoraMain;
import eu.octanne.edora.server.EdoraServerPlayerEntity;
import eu.octanne.edora.server.economy.BankAccount;
import eu.octanne.edora.server.gourvern.Guilde;
import eu.octanne.edora.server.gourvern.Town;
import eu.octanne.edora.server.gourvern.nation.Nation;
import eu.octanne.edora.server.gourvern.nation.NationsManager;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;

@Mixin(ServerPlayerEntity.class)
public class MixinServerPlayerEntity implements EdoraServerPlayerEntity {

    private static final String nationID = "nationID";
    private static final String guildeID = "guildeID";
    private static final String townID = "townID";
    private static final String amountOannes = "amountOannes";
    private static final String edoraTAG = "edora";

    @Unique
    private Nation edoraNation;
    @Unique
    private Town edoraTown;
    @Unique
    private Guilde edoraGuilde;
    @Unique
    private BankAccount edoraBankAccount;

    @Inject(at = @At("RETURN"), method = "<init>(Lnet/minecraft/server/MinecraftServer;Lnet/minecraft/server/world/ServerWorld;"+
    "Lnet/minecraft/server/network/ServerPlayerInteractionManager;)V")
    private void constructClass(MinecraftServer server, ServerWorld world, GameProfile profile, ServerPlayerInteractionManager interactionManager, CallbackInfo info){
        edoraNation = null;
        edoraTown = null;
        edoraGuilde = null;
        edoraBankAccount = new BankAccount(0);
        EdoraMain.log(Level.INFO, "Intialize default EdoraPlayer data.");
    }

    @Inject( method = "readCustomDataFromTag(Lnet/minecraft/nbt/CompoundTag;)V", at = @At("TAIL"))
    public void readCustomDataFromTag(CompoundTag tag, CallbackInfo info) {
        if(tag.contains(edoraTAG)){
            CompoundTag tagEdora = tag.getCompound(edoraTAG);
            if(tagEdora.contains(nationID)) edoraNation = NationsManager.getNationFromID(tagEdora.getUuid(nationID));
            else {
                edoraNation = null;
                EdoraMain.log(Level.INFO, "Nation null");
            }

            if(tagEdora.contains(townID)) edoraTown = Town.getTownFromID(tagEdora.getUuid(townID));
            else {
                edoraTown = null;
                EdoraMain.log(Level.INFO, "Town null");
            }

            if(tagEdora.contains(guildeID)) edoraGuilde = Guilde.getGuildeFromID(tagEdora.getUuid(guildeID));
            else {
                edoraGuilde = null;     
                EdoraMain.log(Level.INFO, "Guilde null");
            }
            // Bank data
            if(tagEdora.contains(amountOannes)) edoraBankAccount.setOannes(tagEdora.getInt(amountOannes));
            EdoraMain.log(Level.INFO, "Loading EdoraPlayer data from .dat file.");
        }
    }

    

	@Inject( method = "writeCustomDataToTag(Lnet/minecraft/nbt/CompoundTag;)V", at = @At("TAIL"))
    public void writeCustomDataToTag(CompoundTag tag, CallbackInfo info) {
        CompoundTag tagEdora = new CompoundTag();
        if(edoraNation != null) {
            tagEdora.putUuid(nationID, edoraNation.getID());
            EdoraMain.log(Level.DEBUG, "ADD nationID : "+edoraNation.getID());
        }
        if(edoraTown != null) {
            tagEdora.putUuid(townID, edoraTown.getID());
            EdoraMain.log(Level.DEBUG, "ADD townID : "+edoraTown.getID());
        }
        if(edoraGuilde != null) {
            tagEdora.putUuid(guildeID, edoraGuilde.getID());
            EdoraMain.log(Level.DEBUG, "ADD guildeID : "+edoraGuilde.getID());
        }
        tagEdora.putInt(amountOannes, edoraBankAccount.getOannes());
        tag.put(edoraTAG, tagEdora);
        EdoraMain.log(Level.INFO, "Saving EdoraPlayer data into .dat file.");
        EdoraMain.log(Level.INFO, "<-- Edora Compound -->\n"+tagEdora.asString());
    }

    

	@Override
	public Nation getNation() {
		return edoraNation;
	}

	@Override
	public void changeNation(Nation nation) {
		edoraNation = nation;
	}

	@Override
	public Town getTown() {
		return edoraTown;
	}

	@Override
	public void changeTown(Town town) {
		edoraTown = town;
	}

	@Override
	public Guilde getGuilde() {
		return edoraGuilde;
	}

	@Override
	public void changeGuilde(Guilde guilde) {
		edoraGuilde = guilde;
	}

	@Override
	public BankAccount getBankAccount() {
		return edoraBankAccount;
	}
}
