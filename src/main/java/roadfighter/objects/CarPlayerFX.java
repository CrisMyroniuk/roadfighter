package roadfighter.objects;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import roadfighter.Config;
import roadfighter.interfaces.Collidator;
import roadfighter.interfaces.Collideable;
import roadfighter.interfaces.Renderable;
import roadfighter.interfaces.Updatable;
import roadfighter.utils.GameObject;

public class CarPlayerFX extends GameObject implements Updatable, Renderable, Collidator {
	private final double ROTATION_FREE_FALL = 20;
	private final double ROTATION_THRESHOLD = 0.6;
	private final double ROTATION_SPEED = 250;

	//private Score score;

	private final int width = 70;
	private final int height = 100;

	private double posX;
	private double posY;
	private double velY = 0;
	private double rotation = 0;
	private double timeStandby = 0;

	private boolean idle = true;
	private boolean dead = false;
	private boolean grounded = false;
	private boolean freeFall = false;

	private Image imageBase;
	private Image imageUp;
	private Image imageDown;
	
	private AudioClip dieAudio;
	private AudioClip hitAudio;
	private AudioClip wingAudio;

	private ImageView render;

	private Rectangle collider;
	private final double colliderTolerance = 0.75;
	private final int colliderWidth = (int) (width * colliderTolerance);
	private final int colliderHeight = (int) (height * colliderTolerance);

	//private final IndividualSpriteAnimation flappyAnimation;
	private final TranslateTransition idleAnimation;
	private final Duration translateDuration = Duration.millis(1000);

	public CarPlayerFX(int x, int y/*, Score score*/) {
		posY = y;
		posX = x;
		//this.score = score;

		initImages();
		//initAudios();
		render = new ImageView(imageBase);
		render.relocate(posX - width / 2, 300);

		collider = new Rectangle(posX - colliderWidth / 2, posY - colliderHeight / 2, colliderWidth, colliderHeight);
		this.idleAnimation = new TranslateTransition();
		collider.setFill(null);
		collider.setStroke(Color.FUCHSIA);
		//flappyAnimation = initFlappyAnimation();
		//idleAnimation = initIdleAnimation();
	}

	private void initImages() {
		
		imageBase = new Image("file:Player.png", width, height, false, false);

		//imageUp = new Image("file:flappy-bird-up.png", width, height, false, false);
		//imageDown = new Image("file:flappy-bird-down.png", width, height, false, false);
		//imageUp = Utils.reColor(imageUp, original, colorRandom);
		//imageBase = Utils.reColor(imageBase, original, colorRandom);
		//imageDown = Utils.reColor(imageDown, original, colorRandom);
	}
	
	/*private void initAudios() {
		dieAudio = AudioResources.getDieAudio();
		hitAudio = AudioResources.getHitAudio();
		wingAudio = AudioResources.getWingAudio();
	}*/

	/*private IndividualSpriteAnimation initFlappyAnimation() {
		IndividualSpriteAnimation individualSpriteAnimation = new IndividualSpriteAnimation(
				new Image[] { imageUp, imageBase, imageDown }, render, Duration.millis(500));
		individualSpriteAnimation.setCustomFrames(new int[] { 0, 1, 2, 1 });
		individualSpriteAnimation.setCycleCount(Animation.INDEFINITE);
		individualSpriteAnimation.play();
		return individualSpriteAnimation;
	}*/

	/*private TranslateTransition initIdleAnimation() {
		TranslateTransition translateTransition = new TranslateTransition(translateDuration, render);
		translateTransition.setCycleCount(Animation.INDEFINITE);
		translateTransition.setFromY(-10);
		translateTransition.setToY(10);
		translateTransition.setAutoReverse(true);
		translateTransition.play();
		return translateTransition;
	}*/

	@Override
	public void update(double deltaTime) {
		timeStandby += deltaTime;
		setY(posY + velY * deltaTime);

		if (!idle) {
			if (!grounded) {
				velY += Config.gravity * deltaTime;
			}

			if (timeStandby > 0) {
				setRotation(Math.min(-30 + timeStandby * ROTATION_SPEED, 90));
			}

			if (rotation > ROTATION_FREE_FALL && !freeFall) {
				freeFall();
			}
		}
	}

	private void freeFall() {
		freeFall = true;
		//flappyAnimation.stop();
		render.setImage(imageBase);
	}

    /*public void push() {
		if (!dead) {
			wingAudio.play();
			idle = false;
			freeFall = false;
			idleAnimation.jumpTo(translateDuration.divide(2));
			idleAnimation.stop();
			//flappyAnimation.play();
			velY = -Config.jumpForce;
			timeStandby = -ROTATION_THRESHOLD;
			setRotation(-20);
		}
	}
/*
	public boolean isDead() {
		return dead;
	}
*/
	private void setY(double posY) {
		this.posY = posY;
		render.setY(posY - height / 2);
		collider.setY(posY - colliderHeight / 2);

	}

	private void setRotation(double rotation) {
		this.rotation = rotation;
		render.setRotate(rotation);
	}

	@Override
	public ImageView getRender() {
		return render;
	}

	@Override
	public Shape getCollider() {
		return collider;
	}

	/*@Override
	public void collide(Collideable collideable) {
		if (!grounded) {
			if (collideable.getClass() == ScoreCollider.class) {
				score.increase();
				((ScoreCollider) collideable).remove();
			} else {
				if (!dead) {
					hitAudio.play();
					dieAudio.play();
					dead = true;
				}
				if (collideable.getClass() == Ground.class) {
					setY(((Rectangle) ((Ground) collideable).getCollider()).getY() - height / 2);
					velY = 0;
					grounded = true;
				}
			}
		}
	}*/

	public double getX() {
		return posX;
	}

	public double getY() {
		return posY;
	}

	public int getHeight() {
		return height;
	}
	
	@Override
	public void destroy() { }

	@Override
	public void collide(Collideable collideable) {
		// TODO Auto-generated method stub
		
	}
}
