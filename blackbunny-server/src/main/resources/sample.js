function test( user )
{

    user.setId( user.getId() + "_old" );

    var newUser = sampleDAO.getUser("bot1");

    var x = 2012

    return String( newUser.getNickname() + x )
}