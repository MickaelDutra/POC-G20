import { Component, OnInit, signal } from '@angular/core';

@Component({
  selector: 'app-ai-floating-menu',
  templateUrl: './ai-floating-menu.html',
  styleUrl: './ai-floating-menu.css',
  standalone: true,
})
export class AiFloatingMenu implements OnInit {
  expanded = signal(true);

  ngOnInit(): void {
    setTimeout(() => {
      this.expanded.set(false);
    }, 10000);
  }
}
