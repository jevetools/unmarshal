/*
 * Copyright (c) 2013, jEVETools
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the author nor the names of the contributors
 *       may be used to endorse or promote products derived from this software
 *       without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.jevetools.unmarshal.api.impl;

/**
 * Copyright (c) 2013, jEVETools.
 * 
 * All rights reserved.
 * 
 * @version 0.0.1
 * @since 0.0.1
 */
public final class Strings
{
  /**
   * @since 0.0.1
   */
  private static final String[] VALUES = { "invalid strings index - null",
      "*corpid", "*locationid", "age", "Asteroid", "authentication", "ballID",
      "beyonce", "bloodlineID", "capacity", "categoryID", "character",
      "characterID", "characterName", "characterType", "charID", "chatx",
      "clientID", "config", "contraband", "corporationDateTime",
      "corporationID", "createDateTime", "customInfo", "description",
      "divisionID", "DoDestinyUpdate", "dogmaIM", "EVE System", "flag",
      "foo.SlimItem", "gangID", "Gemini", "gender", "graphicID", "groupID",
      "header", "idName", "invbroker", "itemID", "items", "jumps", "line",
      "lines", "locationID", "locationName", "macho.CallReq", "macho.CallRsp",
      "macho.MachoAddress", "macho.Notification",
      "macho.SessionChangeNotification", "modules", "name", "objectCaching",
      "objectCaching.CachedObject", "OnChatJoin", "OnChatLeave", "OnChatSpeak",
      "OnGodmaShipEffect", "OnItemChange", "OnModuleAttributeChange",
      "OnMultiEvent", "orbitID", "ownerID", "ownerName", "quantity", "raceID",
      "RowClass", "securityStatus", "Sentry Gun", "sessionchange", "singleton",
      "skillEffect", "squadronID", "typeID", "used", "userID",
      "util.CachedObject", "util.IndexRowset", "util.Moniker", "util.Row",
      "util.Rowset", "*multicastID", "AddBalls", "AttackHit3", "AttackHit3R",
      "AttackHit4R", "DoDestinyUpdates", "GetLocationsEx",
      "InvalidateCachedObjects", "JoinChannel", "LSC", "LaunchMissile",
      "LeaveChannel", "OID+", "OID-", "OnAggressionChange", "OnCharGangChange",
      "OnCharNoLongerInStation", "OnCharNowInStation", "OnDamageMessage",
      "OnDamageStateChange", "OnEffectHit", "OnGangDamageStateChange", "OnLSC",
      "OnSpecialFX", "OnTarget", "RemoveBalls", "SendMessage", "SetMaxSpeed",
      "SetSpeedFraction", "TerminalExplosion", "address", "alert",
      "allianceID", "allianceid", "bid", "bookmark", "bounty", "channel",
      "charid", "constellationid", "corpID", "corpid", "corprole", "damage",
      "duration", "effects.Laser", "gangid", "gangrole", "hqID", "issued",
      "jit", "languageID", "locationid", "machoVersion", "marketProxy",
      "minVolume", "orderID", "price", "range", "regionID", "regionid", "role",
      "rolesAtAll", "rolesAtBase", "rolesAtHQ", "rolesAtOther", "shipid", "sn",
      "solarSystemID", "solarsystemid", "solarsystemid2", "source", "splash",
      "stationID", "stationid", "target", "userType", "userid", "volEntered",
      "volRemaining", "weapon",
      "agent.missionTemplatizedContent_BasicKillMission",
      "agent.missionTemplatizedContent_ResearchKillMission",
      "agent.missionTemplatizedContent_StorylineKillMission",
      "agent.missionTemplatizedContent_GenericStorylineKillMission",
      "agent.missionTemplatizedContent_BasicCourierMission",
      "agent.missionTemplatizedContent_ResearchCourierMission",
      "agent.missionTemplatizedContent_StorylineCourierMission",
      "agent.missionTemplatizedContent_GenericStorylineCourierMission",
      "agent.missionTemplatizedContent_BasicTradeMission",
      "agent.missionTemplatizedContent_ResearchTradeMission",
      "agent.missionTemplatizedContent_StorylineTradeMission",
      "agent.missionTemplatizedContent_GenericStorylineTradeMission",
      "agent.offerTemplatizedContent_BasicExchangeOffer",
      "agent.offerTemplatizedContent_BasicExchangeOffer_ContrabandDemand",
      "agent.offerTemplatizedContent_BasicExchangeOffer_Crafting",
      "agent.LoyaltyPoints", "agent.ResearchPoints", "agent.Credits",
      "agent.Item", "agent.Entity", "agent.Objective", "agent.FetchObjective",
      "agent.EncounterObjective", "agent.DungeonObjective",
      "agent.TransportObjective", "agent.Reward", "agent.TimeBonusReward",
      "agent.MissionReferral", "agent.Location",
      "agent.StandardMissionDetails", "agent.OfferDetails",
      "agent.ResearchMissionDetails", "agent.StorylineMissionDetails", "#196",
      "#197", "#198", "#199", "#200", "#201", "#202", "#203", "#204", "#205",
      "#206", "#207", "#208", "#209", "#210", "#211", "#212", "#213", "#214",
      "#215", "#216", "#217", "#218", "#219", "#220", "#221", "#222", "#223",
      "#224", "#225", "#226", "#227", "#228", "#229", "#230", "#231", "#232",
      "#233", "#234", "#235", "#236", "#237", "#238", "#239", "#240", "#241",
      "#242", "#243", "#244", "#245", "#246", "#247", "#248", "#249", "#250",
      "#251", "#252", "#253", "#254", "#255" };

  /**
   * @param idx
   *            index
   * @return string at position idx
   * @since 0.0.1
   */
  public static String get(final int idx)
  {
    if (idx >= 0 && idx < VALUES.length)
    {
      return VALUES[idx];
    }
    throw new IndexOutOfBoundsException();
  }

  /**
   * @since 0.0.1
   */
  private Strings()
  {
    throw new AssertionError("This constructor must not be called,"
        + " this class is a static utility class.");
  }
}
