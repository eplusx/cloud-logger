package net.eplusx.mimosa.cli

import net.eplusx.mimosa.lib.nature.NatureClient
import net.eplusx.mimosa.lib.switchbot.SwitchBotClient

fun main(args: Array<String>) {
    require(args.isNotEmpty()) { "Usage: mimosa-cli <command> [args...]" }
    when (args[0]) {
        "switchbot" -> {
            require(args.size >= 2) { "Usage: mimosa-cli switchbot <command> [args...]" }
            val client = SwitchBotClient(Secrets.SwitchBot.accessToken, Secrets.SwitchBot.secret)
            when (args[1]) {
                "get-devices" -> println(client.getDevices().toJson())
                "get-meter-status" -> {
                    require(args.size >= 3) { "Usage: mimosa-cli switchbot get-meter-status <device-id>" }
                    println(client.getMeterStatus(args[2]).toJson())
                }
                "get-plug-mini-status" -> {
                    require(args.size >= 3) { "Usage: mimosa-cli switchbot get-plug-mini-status <device-id>" }
                    println(client.getPlugMiniStatus(args[2]).toJson())
                }
                "get-hub2-status" -> {
                    require(args.size >= 3) { "Usage: mimosa-cli switchbot get-hub2-status <device-id>" }
                    println(client.getHub2Status(args[2]).toJson())
                }
                else -> throw IllegalArgumentException("Unknown switchbot command: ${args[1]}")
            }
        }
        "nature" -> {
            require(args.size >= 2) { "Usage: mimosa-cli nature <command> [args...]" }
            val client = NatureClient(Secrets.Nature.accessToken)
            when (args[1]) {
                "get-devices" -> println(client.getDevices().toJson())
                else -> throw IllegalArgumentException("Unknown nature command: ${args[1]}")
            }
        }
        else -> throw IllegalArgumentException("Unknown command: ${args[0]}")
    }
}
