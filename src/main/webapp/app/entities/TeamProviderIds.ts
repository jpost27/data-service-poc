import { Teams } from './Teams';
import { TeamSportsbookSelectionIds } from './TeamSportsbookSelectionIds';
export interface TeamProviderIds {
    teamId: number;
    fanduelTeamId?: number;
    numberfireTeamId?: number;
    sportradarTeamId?: string;
    teams?: Teams;
    teamSportsbookSelectionIds?: TeamSportsbookSelectionIds[];
}
