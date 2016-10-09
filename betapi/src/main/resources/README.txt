GET /available
returns JSON array of bet objects
    [
        {
            "bet_id": 1,
            "event": "World Cup 2016",
            "name": "England",
            "odds": 11.0
        },
        { /* repeated */ },
        { /* repeated */ },
    ]


POST /bets
Accepts JSON bet object with stake
    {
        "bet_id": 1,
        "odds": 11.0,
        "stake": 10
    }

On failure will return 418: I'm a teapot and a JSON error object
    {"error": "Invalid bet ID"}

On success will return 201: Created and a JSON receipt object
    {
        "bet_id": 1,
        "event": "World Cup 2016",
        "name": "England",
        "odds": 11.0,
        "stake": 10,
        "transaction_id": 12345
    }
