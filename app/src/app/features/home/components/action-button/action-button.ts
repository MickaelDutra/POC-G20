import { Component, input } from '@angular/core';

@Component({
  selector: 'app-action-button',
  templateUrl: './action-button.html',
  styleUrl: './action-button.css',
  standalone: true,
})
export class ActionButton {
  icon = input.required<string>();
  label = input.required<string>();
}
