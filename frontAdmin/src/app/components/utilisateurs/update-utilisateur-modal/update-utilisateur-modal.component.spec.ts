import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { UpdateUtilisateurModalComponent } from './update-utilisateur-modal.component';

describe('UpdateUtilisateurModalComponent', () => {
  let component: UpdateUtilisateurModalComponent;
  let fixture: ComponentFixture<UpdateUtilisateurModalComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateUtilisateurModalComponent ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(UpdateUtilisateurModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
