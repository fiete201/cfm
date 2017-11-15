package Cfm::Ui::Cli;
use strict;
use warnings FATAL => 'all';
use Moo;
use Log::Any qw($log);

use Cfm::Autowire;
use Cfm::Config;
use Cfm::Playback::PlaybackService;
use Cfm::Ui::Format::Formatter;

my %command_mapping = (
    list => \&cmd_list,
);

has config => singleton 'Cfm::Config';
has playback_service => singleton 'Cfm::Playback::PlaybackService';
has formatter => singleton 'Cfm::Ui::Format::Formatter';

sub run {
    my ($self) = @_;

    my @args = @ARGV;
    $self->config->add_flags(\@args);
    my ($cmd, $cmdargs) = $self->greedy_match_command(\@args);
    $log->debug("$cmd args: " . join " ", $cmdargs->@*);

    $command_mapping{$cmd}->($self, $cmdargs->@*);
}

sub greedy_match_command {
    my ($self, $command) = @_;
    my $c = scalar($command->@*);
    my $match = "";
    my $match_index = - 1;

    for (my $i = 0; $i < $c; $i++) {
        my $candidate = join "-", @{$command}[0 .. $i];
        if (defined $command_mapping{$candidate}) {
            $match_index = $i;
            $match = $candidate;
        }
    }
    if ($match_index >= 0) {
        return ($match, [ @{$command}[$match_index + 1 .. $c - 1] ])
    } else {
        die $log->error("No such command " . (join "-", $command->@*));
    }
}

### Commands ###

sub cmd_list {
    my ($self) = @_;

    my $playbacks = $self->playback_service->my_playbacks(0);
    $self->formatter->playback_list($playbacks);
}

1;