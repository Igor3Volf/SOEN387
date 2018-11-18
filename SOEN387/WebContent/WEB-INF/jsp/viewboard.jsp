<%@ page language="java" contentType="application/json; charset=UTF-8"
   pageEncoding="UTF-8"%>
{
  "board": {
	"id": ${boards.getGameid()},
		"players": [${boards.getPlayers()[0]}, ${boards.getPlayers()[1]}],
		"decks": [${boards.getDecks()[0]}, ${boards.getDecks()[1]}],
			"play": {
					"${boards.getPlayers()[0]}": {
						"status": "${boards.getPlays()[0].getStatus()}",
						"handsize": ${boards.getPlays()[0].getHandsize()},
						"decksize": ${boards.getPlays()[0].getDecksize()},
						"discardsize": 0,
						"bench": 0
						
					},
					"${boards.getPlayers()[1]}": {
						"status": "${boards.getPlays()[1].getStatus()}",
						"handsize": ${boards.getPlays()[1].getHandsize()},
						"decksize": ${boards.getPlays()[1].getDecksize()},
						"discardsize": 0,
						"bench": 0
												
				}
				
		}
	}
}