package cl.uchile.dcc.citric
package model.controlador
import model.controlador.states.PreGame
import model.units.players.PlayerCharacter
import model.units.traitunits.{Unity, WildUnit}
import model.panels.`trait`.Panel
import model.units.players.CharacterWinEvent
import exceptions.InvalidInputException
import model.board.Board
import scala.math._
import scala.collection.mutable.ArrayBuffer
import scala.math.floor

/**The GameController class represents de Game Controller of the game. Here all the logic of the game is implemented so that it can
 * be functional.
 *
 * @author [[https://github.com/delafte/ Delaney Tello E.]]
 */
class GameController extends Observer[CharacterWinEvent] {
  /**the current state of the game*/
  private var _state: State = new PreGame(this)
  /**The characters that are playing the game*/
  private val _playerCharacters: ArrayBuffer[PlayerCharacter] = ArrayBuffer()
  /**The board of the game*/
  private var board: Board = _
  /**this array keeps track of the current panels of every character*/
  private val _currentPanels: ArrayBuffer[Panel] = ArrayBuffer()

  /**the identifier of the current character*/
  private var currentPlayerNum: Int = 0
  /**the character that is playing during the turn*/
  private var _currentPlayer: Option[PlayerCharacter]=None
  /**The rollResult of the turn*/
  private var _rollResult: Int =0
  /**the current chapter of the game*/
  private var _numChapter: Int=1
  /**The current enemy of the turn*/
  private var _currentEnemy: Option[Unity] = None
  /**Indicates if the effect is from the encounter panel*/
  private var _encounterPanel: Boolean = false
  /**Indicates the number of combat rounds, so that we can limit it to two*/
  private var _numCombat: Int = 0
  private val _defaultUnity: Unity = new PlayerCharacter("Boot",0,0,0,0)
  private val _defaultPlayer: PlayerCharacter = new PlayerCharacter("Boot",0,0,0,0)
  /**Setter de current Player
   * @param p the new current player character*/
  def currentPlayer_=(p:PlayerCharacter):Unit={
    _currentPlayer=Some(p)
  }
  /**Setter for the current state of the game
   * @param s the new state*/
  def state_=(s:State):Unit ={
    _state = s
  }
  /**Getter of the current state of the game*/
  def state: State = _state
  /**A method to add value to the numChapter*/
  def addNumChapter(n:Int):Unit = {
    _numChapter+=max(0,n)
  }
  
  /**A method to add value to the rollResult*/
  def addRollResult(n:Int):Unit = {
    if(n<7 && n>0){
      _rollResult += n
    }
  }

  /**getter for the numCombat parameter*/
  def numCombat: Int = _numCombat
  /**setter for the numCombat parameter
   * @param n the new value of the numCombat*/
  def numCombat_=(n:Int):Unit={
    _numCombat = n
  }
  /**setter of the rollResult variable*/
  def rollRecovery(): Int ={
    _currentPlayer.fold(0)(_.rollDice())
  }

  /**setter of the encounterPanel parameter*/
  def encounterPanel_=(v: Boolean): Unit={
    _encounterPanel = v
  }
  /**getter for the encounter panel parameter*/
  def encounterPanel: Boolean = _encounterPanel
  /**setter for the current enemy
   * @param enemy the new enemy*/
  def currentEnemy_=(enemy: Unity):Unit={
    _currentEnemy = Some(enemy)
  }
  /**getter for the current Enemy*/
  def currentEnemy: Unity = _currentEnemy.getOrElse(_defaultUnity)
  /**getter for the current panel of the turn*/
  def currentPanel: Panel = _currentPanels(currentPlayerNum)
  /**getter for the currentPlayer*/
  def currentPlayer: PlayerCharacter = _currentPlayer.getOrElse(_defaultPlayer)
  /**getter for the players of the game*/
  def playerCharacters: List[PlayerCharacter]=_playerCharacters.toList
  /**the getter for the numChapter variable*/
  def numChapter: Int = _numChapter
  /**getter of rollResult*/
  def rollResult: Int = _rollResult
  /**this method updates the observers of the condition to end a game*/
  override def update(observable: Subject, value: CharacterWinEvent): Unit = {
    val name = value.name_character
    println(s"Game Over! $name has won the Game!")
    _state.reachNorm6()
  }
  /**This method indicates if a character is KO
   * @return the KO state of the current Character*/
  def checkKO():Boolean={
    _currentPlayer.fold(false)(_.KO)
  }
  /**This method adds Players to the game, it creates them with the parameters received
   * @param name the name of the character
   * @param maxHP the maxHp of the character
   * @param attack the attack value of the character
   * @param defense the defense value of the character
   * @param evasion the evasion value of the character */
  private def addPlayerCharacter(name:String, maxHP: Int, attack:Int, defense:Int, evasion:Int): Unit ={
    val character = new PlayerCharacter(name,maxHP, attack, defense, evasion)
    character.addObserverWinEvent(this)
    _playerCharacters += character
  }
  /**This method establishes the player that is going to play next*/
  def nextTurn(): Unit = {
    println("nextTurn")
    currentPlayerNum = (currentPlayerNum + 1) % _playerCharacters.length
    _currentPlayer = Some(playerCharacters(currentPlayerNum))
    _currentPanels(currentPlayerNum).removeCharacter(currentPlayer)

  }
  /**This method starts the game establishing the base conditions
   * @param playersCharacters the parameters to create a PlayerCharacter
   */

  def startGame(playersCharacters: Seq[(String,Int,Int, Int,Int)]): Unit = {
    for((name, maxHP,attack,defense,evasion)<- playersCharacters){
      addPlayerCharacter(name, maxHP,attack, defense, evasion)
    }
    board = new Board(_playerCharacters)
    _currentPlayer=Some(_playerCharacters(0))
    var i=0
    while(i<playerCharacters.length){
      _currentPanels+=_playerCharacters(i).homePanel
       _playerCharacters(i).homePanel.addCharacter(_playerCharacters(i))
      i+=1
    }
    println("Game starts!")
    _state.gameStarts()
  }
  /**this method calls the method of a player character to get their rollDice result*/
  def playerRollDice():Unit = {
    if (_currentPlayer.isDefined){
      _rollResult = _currentPlayer.fold(0)(_.rollDice())
      val result = this.rollResult
      val name = this.name
      println(s"$name's roll was $result")
    }
  }

  /**Returns the name of the Current Player*/
  def name: String =_currentPlayer.fold("None")(_.name)
  /**this method gives to all the characters stars when a new chapter begins*/
  def givePlayerStars(): Unit = {
    val give = (floor(_numChapter/5) +1).toInt
    val name: String=this.name
    println(s"$name! You have gained $give stars!")
    _currentPlayer.foreach(character=>character.addStars(give))

  }
  /**this method upgrades the value of numChapter with 1 unit*/
  def numChapterUpdate():Unit={
    _numChapter+=1
  }
  /**this method follows and updates the movement of the player in the board*/
  def followingMoves(choose:Int): Unit ={
    if(_rollResult>0){
      if(_currentPanels(currentPlayerNum) == _currentPlayer.get.homePanel && numChapter>1){
        val result=askStopDecision(choose)
        if(result==1) _state.stopsMoving()
        else _state.doAction(choose)
      }
      else if(_currentPanels(currentPlayerNum).nextPanels.length!=1){
        _state.choosePath()
        this.askPathDecision(choose)
      }
      else {
        _rollResult -= 1
        _currentPanels(currentPlayerNum) = _currentPanels(currentPlayerNum).nextPanels.head
        followingMoves(choose)
      }
    }
    else{
      _state.outOfMoves()
      _currentPanels(currentPlayerNum).addCharacter(currentPlayer)
      val name= _currentPanels(currentPlayerNum).name
      println(s"You land on a(n) $name")
    }
  }
  /**this method 'asks' for the decision of the player if they want to stop in their homePanel or continue*/
  def askStopDecision(choose:Int):Int={
    if (choose == 1) {
      1
    }
    else if(choose==0){
      _currentPanels(currentPlayerNum) = _currentPanels(currentPlayerNum).nextPanels(choose)
      _rollResult -= 1
      0
    }
    else throw new InvalidInputException(s"$choose is not an option")
  }
  /**this method simulates the decision of the player(input)
   * @param choose the decision('input') of the player*/
  def askPathDecision(choose: Int): Unit = {
    val name= this.name
    println(s"$name, choose a path: ")
    var i=1
    while(i<= _currentPanels(currentPlayerNum).nextPanels.length){
      var j = i-1
      println(s"Option $i: enter $j")
      i+=1
    }
    if(_currentPanels(currentPlayerNum).nextPanels.length > choose){
      println(s"$name's decision is path $choose")
      _currentPanels(currentPlayerNum) = _currentPanels(currentPlayerNum).nextPanels(choose)
      _rollResult-=1
      _state.doAction(choose)
    }
    else throw new InvalidInputException(s"$choose is not an option")
  }

  /** This method simulates the process of asking the decision of a player, so that the decision of attack a character or not
   * can be implemented. This function is called before the landing on panel state.*/
  def attackDecision(choose: Int): Int = {
    var name = this.name
    println(s"$name, do you want to start a combat? '0' if not, '1' if yes")
    if(choose==0) 0
    else if(choose == 1) 1
    else throw new InvalidInputException(s"$choose is not an option")
  }
  def doAttack(choose:Int):Unit={
    if(!encounterPanel && choose ==0){
      val name=currentPlayer.name
      println(s"$name is going to Attack")
      println("The enemy has to choose their response:")
      decideDefendOrEvade(choose, _currentEnemy.get)
    }
    if(choose == 0 && encounterPanel) {
      println("you are going to attack a wild unit!")
       _currentPlayer.foreach(character => _currentEnemy.foreach(target => character.Attack(target)))
    }
    else {
      println("The Enemy is going to attack back!")
      decideDefendOrEvade(choose, _currentPlayer.get)
      _currentEnemy.foreach(target => _currentPlayer.foreach(character=>target.Attack(character)))
      println("End of Combat")
      val hp=currentPlayer.CurrentHP
      val hp2 = currentEnemy.CurrentHP
      val name = currentPlayer.name
      println(s"$name's current HP is $hp")
      println(s"the enemy's current hp is $hp2")
    }
    _numCombat+=1
  }
  def effectPanel(): Unit ={
    val name = this.name
    if(!encounterPanel){
      println(s"$name, now you are going to receive the panel's effect")
      _currentPanels(currentPlayerNum).apply(_currentPlayer.getOrElse(throw new AssertionError("There's no currentPlayer")), this)
      if(!encounterPanel)_state.effectApplied()
    }
  }


/** This method asks for the input of the user, so that the decision of defend or evade an attack
   * can be implemented. This function is called during the Attack process. It changes the values of the attributes
   * Defend or Evade. */
  def decideDefendOrEvade(choose: Int, chooser: Unity): Unit = {
    println("Put 1 if you want to defend, 0 if you want to evade")
    if(choose == 1) {
      chooser.defendOrEvade=false
      println("Defend!")
    }
    else if(choose == 0){
      chooser.defendOrEvade=true
      println("Evade")
    }
    else throw new InvalidInputException(s"$choose is not an option")
  }
  /**This method checks if there are more players in a panel. This is used when a character lands on a panel*/
  def checkMorePlayersInPanel(): Boolean = {
    var j = 0
    while (j < _currentPanels.length) {
      if (_currentPanels(currentPlayerNum) == _currentPanels(j) && currentPlayerNum != j) {
         return true
      }
      j += 1
    }
    false
  }
  /**asks the player to choose the enemy for the combat*/
  def selectEnemy(choose: Int):Unit={
      println("Select a character to attack")
      var i = 0
      while (i < playerCharacters.length) {
        if (i != currentPlayerNum) {
          val pers: String = playerCharacters(i).name
          println(s"$i: $pers")
        }
        i += 1
      }
      if (choose > playerCharacters.length) {
        throw new InvalidInputException(s"$choose is not an option")
      }
      else {
        _currentEnemy = Some(playerCharacters(choose - 1))
      }
      _state.attacks()
  }
  /**Calls the doAction of the current state*/
  def doAction(choose:Int): Unit = _state.doAction(choose)
  def inPreGame(): Boolean = _state.inPreGame()
  def inChapter(): Boolean = _state.inChapter()
  def inCombat(): Boolean = _state.inCombat()
  def inEndOfGame(): Boolean = _state.inEndOfGame()
  def inMoving(): Boolean = _state.inMoving()
  def inOnPanel(): Boolean = _state.inOnPanel()
  def inPlayerTurn(): Boolean = _state.inPlayerTurn()
  def inRecovery(): Boolean = _state.inRecovery()
  def inWait(): Boolean = _state.inWait()
  def setState(s:State): Unit = {
    _state=s
    s.setGameController(this)
  }

  /** This method is for the transition from pre-game state to Chapter state */
  def gameStarts(): Unit = _state.gameStarts()
  /**This method is for the transition from the OnPanel state to the Combat state*/
  def fightWildUnit():Unit = _state.fightWildUnit()
  /** This method is for staying in Chapter State */
  def newChapter(): Unit = _state.newChapter()

  /** This method is for the transition from the chapter state to the End of Game state */
  def reachNorm6(): Unit = _state.reachNorm6()

  /** This method is for the transition from the Chapter state to de Player Turn state */
  def startTurnPlayer(): Unit = _state.startTurnPlayer()
  /** This method is for the transition from the Chapter state to de Recovery state */

  def playerKO():Unit = _state.playerKO()

  /** this method is for the transition from the PlayerTurn State to the Moving state */
  def rollD(): Unit = _state.rollD()

  /** This method is for the transition form the Recovery state to the Chapter State */
  def insufficientRoll(): Unit = _state.insufficientRoll()

  /** This method is for the transition from the Recovery state to the PlayerTurn state */
  def sufficientRoll(): Unit = _state.sufficientRoll()

  /** this method is for the transition from the Moving state to the OnPanel state */
  def stopsMoving(): Unit = _state.stopsMoving()

  /** this method is for the transition from the Moving state to the OnPanel state */
  def outOfMoves(): Unit = _state.outOfMoves()

  /** this method is for the transition from the OnPanel state to the Combat state */
  def decideNotFightCharacter(): Unit = _state.decideNotFightCharacter()

  /** this method is for the transition from the Combat state to the OnPanel state */
  def endCombat(): Unit = _state.endCombat()

  /** this method is for the transition from the Combat state to the wait state */
  def attacks(): Unit = _state.attacks()

  /** this method is for the transition from the Wait state to the Combat state */
  def response(): Unit = _state.response()


  /** this method is for the transition from the OnPanel state to the Chapter state */
  def effectApplied(): Unit = _state.effectApplied()
  /**this method is for the transition from the Moving state to the Moving state*/
  def choosePath(): Unit = _state.choosePath()
}
